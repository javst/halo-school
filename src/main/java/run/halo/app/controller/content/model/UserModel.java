package run.halo.app.controller.content.model;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import run.halo.app.model.dto.AttachmentDTO;
import run.halo.app.model.entity.Compete;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.User;
import run.halo.app.service.ThemeService;
import run.halo.app.service.impl.CompeteServiceImpl;
import run.halo.app.service.impl.OrderServiceImpl;
import run.halo.app.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class UserModel {

    private final ThemeService themeService;

    private final HttpServletRequest httpServletRequest;

    private final OrderServiceImpl orderService;

    private final CompeteServiceImpl competeService;

    private final UserServiceImpl userService;

    public UserModel(ThemeService themeService,
        HttpServletRequest httpServletRequest,
        OrderServiceImpl orderService,
        CompeteServiceImpl competeService,
        UserServiceImpl userService) {
        this.themeService = themeService;
        this.httpServletRequest = httpServletRequest;
        this.orderService = orderService;
        this.competeService = competeService;
        this.userService = userService;
    }

    public String initUserPage(String action , Model model) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            return themeService.render("index");
        }
        if (action.equals("order")){
            List<Order> orderServiceByUsername = orderService.findByUsername(user.getUsername());
            model.addAttribute("order",orderServiceByUsername);
        }
        if (action.equals("apply")){
            List<Compete> competeList = competeService.findBuyUserId(user.getId());
            model.addAttribute("apply",competeList);

        }
        model.addAttribute("action",action);
        return themeService.render("user");

    }

    public String updateUser(String nickname, String student_num, String department,
        String class_name, String phoneNumber) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            return themeService.render("index");
        }
        user.setNickname(nickname);
        user.setStudent_num(student_num);
        user.setDepartment(department);
        user.setClass_name(class_name);
        user.setPhoneNumber(phoneNumber);
        user = userService.update(user);
        session.setAttribute("user",user);
        return "redirect:/user/userInformation";
    }

    public String updateUserAvatar(AttachmentDTO attachmentDTO) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            return themeService.render("index");
        }
        user.setAvatar(attachmentDTO.getPath());
        user = userService.update(user);
        session.setAttribute("user",user);
        return  "redirect:/user/userInformation";
    }

    public String updateOrder(Integer orderId, Integer amount) {
        Order order = orderService.getById(orderId);
        order.setAmount(amount);
        try {
            orderService.update(order);
        }catch (Exception e)
        {
            return e.toString();
        }
        return "0";

    }
}
