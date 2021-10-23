package run.halo.app.controller.content;

import java.net.http.HttpClient;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.aliyun.oss.common.comm.ServiceClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import run.halo.app.controller.content.model.PostModel;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.User;
import run.halo.app.model.enums.MFAType;
import run.halo.app.model.enums.PostPermalinkType;
import run.halo.app.model.params.LoginParam;
import run.halo.app.repository.PostRepository;
import run.halo.app.security.token.AuthToken;
import run.halo.app.service.AdminService;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.service.impl.AdminServiceImpl;
import run.halo.app.service.impl.OrderServiceImpl;
import run.halo.app.service.impl.PostServiceImpl;
import run.halo.app.service.impl.UserServiceImpl;
import run.halo.app.utils.BCrypt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Blog index page controller
 *
 * @author ryanwang
 * @date 2019-03-17
 */
@Slf4j
@Controller
@RequestMapping
public class ContentIndexController {

    private final PostService postService;

    private final OptionService optionService;

    private final PostModel postModel;

    private final UserServiceImpl userService;


    private final AdminServiceImpl adminService;
    private final HttpServletRequest httpServletRequest ;

    private final OrderServiceImpl orderService;

    public ContentIndexController(PostService postService,
        OptionService optionService,
        PostModel postModel,
        UserServiceImpl userService,
        HttpServletRequest httpServletRequest,
        AdminServiceImpl adminService,
        OrderServiceImpl orderService
       ) {
        this.postService = postService;
        this.optionService = optionService;
        this.postModel = postModel;
        this.userService = userService;
        this.adminService = adminService;
        this.httpServletRequest = httpServletRequest;
        this.orderService = orderService;

    }


    /**
     * Render blog index
     *
     * @param p post id
     * @param model model
     * @return template path: themes/{theme}/index.ftl
     */
    @GetMapping()
    public String index(Integer p, String token, Model model) {


        PostPermalinkType permalinkType = optionService.getPostPermalinkType();


        String tokenSession = null;
        HttpSession session = httpServletRequest.getSession();
        try {
            tokenSession = session.getAttribute("token").toString();
        }catch (Exception e) {
            System.out.println(e);

        }
        if (token==null){
            if (tokenSession == null){
                session.setAttribute("is_login",false);
            }else {
                token = tokenSession;
                session.setAttribute("is_login",true);
            }

        }else {
            model.addAttribute("is_login",true);
        }
        if (PostPermalinkType.ID.equals(permalinkType) && !Objects.isNull(p)) {
            Post post = postService.getById(p);
            return postModel.content(post, token, model);
        }

        return this.index(model, 1);
    }

    /**
     * "username":$("#register-username").val(),
     *                                 "nickname":$("#register-nickname").val(),
     *                                 "password":$("#register-password").val(),
     *                                 "department":$("#register-department").val(),
     *                                 "class_name":$("#register-class_name").val(),
     *                                 "student_num":$("#register-student_num").val(),
     * @param
     * @return
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public String register(
        @RequestParam(name = "username") Object username,
        @RequestParam(name = "nickname") Object nickname,
        @RequestParam(name = "password") Object password,
        @RequestParam(name = "department") Object department,
        @RequestParam(name = "class_name") Object class_name,
        @RequestParam(name = "student_num") Object student_num
        ){
        if(username.toString().length()<=2){
            return "用户名过短";
        }
        if(password.toString().length()<=8){
            return "密码过短";
        }
        if(department.toString().length()<=5){
            return "请正确输入院系";
        }
        if(nickname.toString().length()<=2){
            return "请输入真实姓名";
        }
        val user = new User();
        user.setRule(1);
        user.setMoney(400.0);
        user.setDepartment(department.toString());
        user.setUsername(username.toString());
        user.setNickname(nickname.toString());
        user.setPassword(BCrypt.hashpw(password.toString(), BCrypt.gensalt()));
        user.setClass_name(class_name.toString());
        user.setStudent_num(student_num.toString());
        user.setMfaType(MFAType.NONE);


        try {
            Optional<User> byUsername = userService.getByUsername(username.toString());
            if (user == null){
                userService.create(user);
            }else {
                return "用户已经存在";
            }

        }catch (Exception e){
            return e.getMessage().toString();
        }

        return "注册成功";
    }
    @PostMapping(value = "/login")
    @ResponseBody()
    public String login( Model model, @RequestParam(name = "username") Object username,
        @RequestParam(name = "password") Object password
        ,String token,Integer p){

        System.out.println(username);
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername(username.toString());
        loginParam.setPassword(password.toString());

        HttpSession session = httpServletRequest.getSession();
        User authenticate = null;
        try {
            authenticate = adminService.authenticate(loginParam);
            AuthToken authToken = adminService.authCodeCheck(loginParam);
            token = authToken.getAccessToken();
        }catch (Exception e){
            return e.getMessage().toString();

        }
        System.out.println(authenticate);
        if (authenticate!=null){
            session.setAttribute("is_login",true);
            session.setAttribute("user",authenticate);
            session.setAttribute("token",token);
            return ("登录成功");
        }
        return ("登录失败");

    }

    /**
     * Render blog index
     *
     * @param model model
     * @param page current page number
     * @return template path: themes/{theme}/index.ftl
     */
    @GetMapping(value = "page/{page}")
    public String index(Model model,
        @PathVariable(value = "page") Integer page) {

        return postModel.list(page, model);
    }
    @GetMapping(value = "/logout")
    @ResponseBody
    public String logout(Model model){
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("is_login",false);
        session.removeAttribute("user");
        session.removeAttribute("token");
        return "注销成功";
    }
    @PostMapping(value = "/buyGoods")
    @ResponseBody
    public String buyGoods(@RequestParam("postId") int postId){

        HttpSession session = httpServletRequest.getSession();
        User user;
        try {
            user = (User) session.getAttribute("user");
        }catch (Exception e){
            return e.getMessage().toString();
        }
        Post post;
        if (user != null){
            post = postService.getById(postId);
            if (post!=null){
                if (user.getMoney()-post.getPrice()>=0)
                {
                    user.setMoney(user.getMoney()-post.getPrice());
                }else {
                    return "余额不足";
                }

                try{
                    User updateUser = userService.update(user);
                    session.setAttribute("user",user);
                    post.setStock(post.getStock()-1);
                    postService.update(post);
                    Order order = new Order();
                    order.setMoney(post.getPrice());
                    order.setState(0);
                    order.setUsername(user.getNickname());
                    order.setClass_name(user.getClass_name());
                    order.setDepartment(user.getDepartment());
                    order.setUserId(user.getId());
                    orderService.create(order);
                    return "申请设备成功，请等待老师审核";

                }catch (Exception e){
                    return e.getMessage().toString();
                }
            }

        }else {
            return "请先登录";
        }






        return "申请失败";
    }
}
