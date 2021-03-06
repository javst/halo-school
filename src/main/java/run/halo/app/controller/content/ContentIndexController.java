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
import run.halo.app.model.entity.Compete;
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
import run.halo.app.service.impl.CompeteServiceImpl;
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

    private final HttpServletRequest httpServletRequest;

    private final OrderServiceImpl orderService;

    private final CompeteServiceImpl competeService;

    public ContentIndexController(PostService postService,
        OptionService optionService,
        PostModel postModel,
        UserServiceImpl userService,
        HttpServletRequest httpServletRequest,
        AdminServiceImpl adminService,
        OrderServiceImpl orderService,
        CompeteServiceImpl competeService
    ) {
        this.postService = postService;
        this.optionService = optionService;
        this.postModel = postModel;
        this.userService = userService;
        this.adminService = adminService;
        this.httpServletRequest = httpServletRequest;
        this.orderService = orderService;
        this.competeService = competeService;

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
        User user = new User();
        HttpSession session = httpServletRequest.getSession();
        try {
            user = (User)session.getAttribute("user");
        }catch (Exception e){
            System.out.println(e);
        }
        if(user!=null){
            session.setAttribute("user",user);
        }
        if (PostPermalinkType.ID.equals(permalinkType) && !Objects.isNull(p)) {
            Post post = postService.getById(p);
            return postModel.content(post, token, model);
        }

        return this.index(model, 1);
    }

    /**
     * "username":$("#register-username").val(),
     * "nickname":$("#register-nickname").val(),
     * "password":$("#register-password").val(),
     * "department":$("#register-department").val(),
     * "class_name":$("#register-class_name").val(),
     * "student_num":$("#register-student_num").val(),
     *
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
    ) {
        if (username.toString().length() <= 2) {
            return "???????????????";
        }
        if (password.toString().length() <= 8) {
            return "????????????";
        }
        if (department.toString().length() <= 5) {
            return "?????????????????????";
        }
        if (nickname.toString().length() < 2) {
            return "?????????????????????";
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
        final Optional<User> byUsername = userService.getByUsername(username.toString());
        System.out.println(byUsername);
        if (byUsername.isEmpty()) {
            try {
                userService.create(user);
                return "????????????";
            } catch (Exception e) {
                return e.getMessage().toString();
            }
        } else {
            return "???????????????";
        }
    }


    @PostMapping(value = "/compete")
    @ResponseBody
    public String apply(@RequestParam("title") String title,
        @RequestParam("norm") String norm, @RequestParam("link") String link,
        @RequestParam("number") Integer number) {
        if (title.length() > 0 && norm.length() > 0 && link.length() > 0 && number > 0) {
            HttpSession session = httpServletRequest.getSession();
            User user;
            user = (User) session.getAttribute("user");
            if (user == null){
                return "????????????";
            }
            Compete compete = new Compete();
            compete.setLink(link);
            compete.setNorm(norm);
            compete.setUserId(user.getId());
            compete.setNumber(number);
            compete.setTitle(title);
            try {
                compete.setUsername(user.getNickname());
                competeService.create(compete);
                return "????????????";
            }catch (Exception e)
            {
                return e.toString();
            }

        }else {
            return "?????????????????????";
        }

    }

    @PostMapping(value = "/login")
    @ResponseBody()
    public String login(Model model, @RequestParam(name = "username") Object username,
        @RequestParam(name = "password") Object password
        , String token, Integer p) {
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername(username.toString());
        loginParam.setPassword(password.toString());
        HttpSession session = httpServletRequest.getSession();
        User authenticate = null;
        try {
            authenticate = adminService.authenticate(loginParam);
            AuthToken authToken = adminService.authCodeCheck(loginParam);
            token = authToken.getAccessToken();
        } catch (Exception e) {
            return e.getMessage().toString();

        }
        if (authenticate != null) {
            session.setAttribute("is_login", true);
            session.setAttribute("user", authenticate);
            return ("????????????");
        }
        return ("????????????");

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
    public String logout(Model model) {
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("is_login");
        session.removeAttribute("user");
        session.removeAttribute("token");
        return "????????????";
    }

    @PostMapping(value = "/buyGoods")
    @ResponseBody
    public String buyGoods(@RequestParam("postId") int postId, @RequestParam("amount") int amount) {
        HttpSession session = httpServletRequest.getSession();
        User user;
        try {
            user = (User) session.getAttribute("user");
        } catch (Exception e) {
            return e.getMessage().toString();
        }
        Post post;
        if (user != null) {
            post = postService.getById(postId);
            if (post != null && amount > 0) {
                if (user.getMoney() - post.getPrice() * amount >= 0) {
                    user.setMoney(user.getMoney() - post.getPrice());
                } else {
                    return "????????????";
                }
                try {
                    User updateUser = userService.update(user);
                    session.setAttribute("user", user);
                    post.setStock(post.getStock() - amount);
                    postService.update(post);
                    Order order = new Order();
                    order.setMoney(post.getPrice() * amount);
                    order.setDevice_id(post.getId());
                    order.setDevice(post.getTitle());
                    order.setAmount(amount);
                    order.setState(0);
                    order.setNorm(post.getNorms());
                    order.setStudent_num(user.getStudent_num());
                    order.setUsername(user.getNickname());
                    order.setClass_name(user.getClass_name());
                    order.setDepartment(user.getDepartment());
                    order.setUserId(user.getId());
                    orderService.create(order);
                    return "??????????????????????????????????????????";
                } catch (Exception e) {
                    return e.getMessage().toString();
                }
            }
        } else {
            return "????????????";
        }
        return "????????????";
    }
}
