package run.halo.app.controller.admin.api;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import run.halo.app.model.entity.Hero;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.User;
import run.halo.app.service.impl.HeroServiceImpl;
import run.halo.app.service.impl.OrderServiceImpl;
import run.halo.app.service.impl.UserServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/api/admin/hero")
public class HeroController {

    private final HeroServiceImpl heroService;

    private final UserServiceImpl userService;

    public HeroController(HeroServiceImpl heroService, UserServiceImpl userService) {
        this.heroService = heroService;
        this.userService = userService;

    }

    @GetMapping("latest")
    @ApiOperation("Pages latest post")
    public List<Hero> getLatest(@RequestParam(name = "start", defaultValue = "0") int start,
        @RequestParam(name = "top", defaultValue = "20") int top) {


        final List<Hero> latest = heroService.findLatest(start, top);

        return latest;
    }

    @PostMapping("passHero")
    @ApiOperation("pass order")
    public String passOrder(@RequestParam("id") Integer id, @RequestParam("state") Integer state) {
        Integer i = heroService.passOrder(id, state);
        final Hero hero = heroService.getById(id);
        User user = userService.getById(hero.getUserId());
        user.setMoney(hero.getMoney().doubleValue() + user.getMoney());
        userService.update(user);
        return i.toString();
    }


    @PostMapping("refuseHero")
    @ApiOperation("pass order")
    public String refuseApply(
        @RequestParam("id") Integer id,
        @RequestParam("state") Integer state,
        @RequestParam("advice") String advice) {
        Integer i = heroService.refuseApply(id, state, advice);
        return i.toString();
    }

    @PostMapping("countHero")
    @ApiOperation("count order")
    public int countHero() {
        final int i = heroService.countOrders();
        return i;
    }


    @PostMapping("deleteHero")
    @ApiOperation("delete order")
    public String deleteOrder(@RequestParam("id") Integer id) {
        final Integer i = heroService.deleteOrder(id);

        return i.toString();
    }

    @PostMapping("queryHero")
    @ApiOperation("query order")
    public List<Hero> queryOrder(
        @RequestParam(name = "username", defaultValue = "") String username,
        @RequestParam(name = "state", defaultValue = "-1") Integer state,
        @RequestParam(name = "createTime", defaultValue = "") String createTime) {


        System.out.println(state + "\n");
        System.out.println(createTime + "\n");
        System.out.println(username + "\n");

        if (username.length() == 0 && state == -1 && createTime.length() == 0) {
            return null;
        } else if (username.length() != 0 && state == -1 && createTime.length() == 0) {
            System.out.println(1);
            return heroService.findByUsername(username);
        } else if (username.length() != 0 && state != -1 && createTime.length() == 0) {
            System.out.println(2);
            return heroService.findByStateAndUsername(state, username);
        } else if (username.length() != 0 && state == -1 && createTime.length() != 0) {
            System.out.println(3);
            return heroService.findByUsernameAndCreateTime(username, createTime);
        } else if (username.length() == 0 && state != -1 && createTime.length() == 0) {
            System.out.println(4);
            return heroService.findByState(state);
        } else if (username.length() == 0 && state != -1 && createTime.length() != 0) {
            System.out.println(5);
            return heroService.findByStateAndCreateTime(state, createTime);
        } else if (username.length() == 0 && state == -1 && createTime.length() != 0) {
            System.out.println(6);
            return heroService.findByCreateTime(createTime);
        } else {
            System.out.println(7);
            return heroService.findByCreateTimeAndUsernameAndState(username, createTime, state);
        }


    }
}
