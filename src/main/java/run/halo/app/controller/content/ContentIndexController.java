package run.halo.app.controller.content;

import java.net.http.HttpClient;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.aliyun.oss.common.comm.ServiceClient;
import lombok.extern.slf4j.Slf4j;
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
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.User;
import run.halo.app.model.enums.PostPermalinkType;
import run.halo.app.model.params.LoginParam;
import run.halo.app.security.token.AuthToken;
import run.halo.app.service.AdminService;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.service.impl.AdminServiceImpl;
import run.halo.app.service.impl.UserServiceImpl;
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

    public ContentIndexController(PostService postService,
        OptionService optionService,
        PostModel postModel,
        UserServiceImpl userService,
        HttpServletRequest httpServletRequest,
        AdminServiceImpl adminService) {
        this.postService = postService;
        this.optionService = optionService;
        this.postModel = postModel;
        this.userService = userService;
        this.adminService = adminService;
        this.httpServletRequest = httpServletRequest;
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
    @PostMapping(value = "/login")
    public RedirectView login( Model model, @RequestParam(name = "user") List<String> user,String token,Integer p){

        System.out.println(user);
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername(user.get(0));
        loginParam.setPassword(user.get(1));

        HttpSession session = httpServletRequest.getSession();
        User authenticate = null;
        try {
            authenticate = adminService.authenticate(loginParam);
            AuthToken authToken = adminService.authCodeCheck(loginParam);
            token = authToken.getAccessToken();
        }catch (Exception e){

        }
        System.out.println(authenticate);
        if (authenticate!=null){

            session.setAttribute("is_login",true);
            session.setAttribute("user",authenticate);
            session.setAttribute("token",token);


        }
        return new RedirectView("/") ;

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
}
