package run.halo.app.controller.admin.api;

import com.qiniu.util.Json;
import com.qiniu.util.StringMap;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.annotation.DisableOnCondition;
import run.halo.app.cache.lock.CacheLock;
import run.halo.app.exception.BadRequestException;
import run.halo.app.model.dto.UserDTO;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.User;
import run.halo.app.model.enums.MFAType;
import run.halo.app.model.params.MultiFactorAuthParam;
import run.halo.app.model.params.PasswordParam;
import run.halo.app.model.params.UserParam;
import run.halo.app.model.support.BaseResponse;
import run.halo.app.model.support.UpdateCheck;
import run.halo.app.model.vo.MultiFactorAuthVO;
import run.halo.app.service.UserService;
import run.halo.app.service.impl.UserServiceImpl;
import run.halo.app.utils.HaloUtils;
import run.halo.app.utils.TwoFactorAuthUtils;
import run.halo.app.utils.ValidationUtils;
import java.util.List;
import java.util.Map;

/**
 * User controller.
 *
 * @author johnniang
 * @date 2019-03-19
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    public UserController(UserService userService,UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("latest")
    @ApiOperation("Pages latest post")
    public List<User> getLatest(@RequestParam(name = "start",defaultValue = "0") int start , @RequestParam(name = "top",defaultValue = "20") int top ){


        final List<User> latest = userServiceImpl.findLatest(start , top);

        return latest;
    }

    @PostMapping("countUser")
    @ApiOperation("count order")
    public int countUser(){
        final int i = userServiceImpl.countUser();
        return i;
    }

    @PostMapping("deleteUser")
    @ApiOperation("delete user")
    public String deleteUser(@RequestParam("id") Integer id){
        final User byId = userServiceImpl.getById(id);
        try{
            userServiceImpl.remove(byId);
        }catch (Exception e)
        {
            return e.getMessage().toString();
        }
        return "1";
    }

    @PostMapping("updateUser")
    @ApiOperation("update user")
    public Integer updateUser(@RequestParam("user") String userString,@RequestParam("userId") Integer userId){
        try {
            final StringMap user = Json.decode(userString);
            final User userT = userServiceImpl.getById(userId);
            userT.setDepartment(user.get("department").toString());
            userT.setMoney(Double.parseDouble(user.get("money").toString()));
            userT.setNickname(user.get("username").toString());
            userT.setClass_name(user.get("class_name").toString());
            userT.setStudent_num(user.get("student_num").toString());
            userServiceImpl.update(userT);
            return 1;
        }catch (Exception e){
            return 0;
        }


    }
    @PostMapping("queryUser")
    @ApiOperation("query user")
    public List<User> queryOrder(@RequestParam(value = "username",defaultValue = "") String nickname,
        @RequestParam(value = "student_num",defaultValue = "") String student_num){
        if (nickname.length()>0&&student_num.length()>0){
            return userServiceImpl.getByNicknameAndNickname(nickname,student_num);

        }else if (nickname.length()>0){
            return userServiceImpl.getByNickname(nickname);
        }else if (student_num.length()>0){
            return userServiceImpl.getByStudent_num(student_num);
        }


        return null;
    }

    @GetMapping("profiles")
    @ApiOperation("Gets user profile")
    public UserDTO getProfile(User user) {
        return new UserDTO().convertFrom(user);
    }

    @PutMapping("profiles")
    @ApiOperation("Updates user profile")
    @DisableOnCondition
    public UserDTO updateProfile(@RequestBody UserParam userParam, User user) {
        // Validate the user param
        ValidationUtils.validate(userParam, UpdateCheck.class);

        // Update properties
        userParam.update(user);

        // Update user and convert to dto
        return new UserDTO().convertFrom(userService.update(user));
    }

    @PutMapping("profiles/password")
    @ApiOperation("Updates user's password")
    @DisableOnCondition
    public BaseResponse<String> updatePassword(@RequestBody @Valid PasswordParam passwordParam,
        User user) {
        userService.updatePassword(passwordParam.getOldPassword(), passwordParam.getNewPassword(),
            user.getId());
        return BaseResponse.ok("密码修改成功");
    }

    @PutMapping("mfa/generate")
    @ApiOperation("Generate Multi-Factor Auth qr image")
    @DisableOnCondition
    public MultiFactorAuthVO generateMFAQrImage(
        @RequestBody MultiFactorAuthParam multiFactorAuthParam, User user) {
        if (MFAType.NONE == user.getMfaType()) {
            if (MFAType.TFA_TOTP == multiFactorAuthParam.getMfaType()) {
                String mfaKey = TwoFactorAuthUtils.generateTFAKey();
                String optAuthUrl =
                    TwoFactorAuthUtils.generateOtpAuthUrl(user.getNickname(), mfaKey);
                String qrImageBase64 = "data:image/png;base64,"
                    + Base64Utils.encodeToString(
                    HaloUtils.generateQrCodeToPng(optAuthUrl, 128, 128));
                return new MultiFactorAuthVO(qrImageBase64, optAuthUrl, mfaKey, MFAType.TFA_TOTP);
            } else {
                throw new BadRequestException("暂不支持的 MFA 认证的方式");
            }
        } else {
            throw new BadRequestException("MFA 认证已启用，无需重复操作");
        }
    }

    @PutMapping("mfa/update")
    @ApiOperation("Updates user's Multi Factor Auth")
    @CacheLock(autoDelete = false, prefix = "mfa")
    @DisableOnCondition
    public MultiFactorAuthVO updateMFAuth(
        @RequestBody @Valid MultiFactorAuthParam multiFactorAuthParam, User user) {
        if (StringUtils.isNotBlank(user.getMfaKey())
            && MFAType.useMFA(multiFactorAuthParam.getMfaType())) {
            return new MultiFactorAuthVO(MFAType.TFA_TOTP);
        } else if (StringUtils.isBlank(user.getMfaKey())
            && !MFAType.useMFA(multiFactorAuthParam.getMfaType())) {
            return new MultiFactorAuthVO(MFAType.NONE);
        } else {
            final String mfaKey = StringUtils.isNotBlank(user.getMfaKey()) ? user.getMfaKey() :
                multiFactorAuthParam.getMfaKey();
            TwoFactorAuthUtils.validateTFACode(mfaKey, multiFactorAuthParam.getAuthcode());
        }
        // update MFA key
        User updateUser = userService
            .updateMFA(multiFactorAuthParam.getMfaType(), multiFactorAuthParam.getMfaKey(),
                user.getId());

        return new MultiFactorAuthVO(updateUser.getMfaType());
    }
}
