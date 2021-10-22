package run.halo.app.post;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.Post;
import run.halo.app.model.entity.User;
import run.halo.app.model.params.LoginParam;
import run.halo.app.repository.OrderRepository;
import run.halo.app.repository.PostRepository;
import run.halo.app.repository.UserRepository;
import run.halo.app.security.token.AuthToken;
import run.halo.app.service.PostService;
import run.halo.app.service.impl.AdminServiceImpl;
import run.halo.app.service.impl.OrderServiceImpl;
import run.halo.app.service.impl.UserServiceImpl;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class test {
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderServiceImpl orderService;
    @Test
    void getOrder(){


        Order order1 =new Order();
        Order byId = orderService.getById(1);

        byId.setId(null);
        byId.setMoney(100.0);
        byId.setUsername("sss");
        Order order = orderService.create(byId);
        byId = orderService.getById(8);


        System.out.println(byId.getCreateTime());
    }


    @Test
    void getUser(){
        User byId = userService.getById(1);
        System.out.println(byId );
        byId.setId(null);
        byId.setEmail("xxx@wsw.cool");
        byId.setUsername("111");
        System.out.println(byId.getPassword());

    }
    @Test
    void verifyUser(){
        String username = "wcool";
        String password = "wu1997326";
        LoginParam loginParam = new LoginParam();
        loginParam.setUsername(username);
        loginParam.setPassword(password);
        User authenticate = null;
        try{
            authenticate = adminService.authenticate(loginParam);
            AuthToken authToken = adminService.authCodeCheck(loginParam);
            System.out.println(authToken);

        }catch (Exception e){

            String message = e.getMessage();
            System.out.println(message);
        }finally {

        }



    }

    @Test
    void getPost(){
        Post p = new Post();

        Optional<Post> byId1 = postRepository.findById(1);
        System.out.println(byId1);

        // Post byId = postService.getById(1);
        // System.out.println(byId.getPrice()+"   " +byId.getStock());
        // System.out.println(byId.toString());
    }
}
