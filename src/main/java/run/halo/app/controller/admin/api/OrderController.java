package run.halo.app.controller.admin.api;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.User;
import run.halo.app.repository.OrderRepository;
import run.halo.app.service.impl.OrderServiceImpl;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;

    }

    @GetMapping("latest")
    @ApiOperation("Pages latest post")
    public List<Order> getLatest(@RequestParam(name = "start", defaultValue = "0") int start,
        @RequestParam(name = "top", defaultValue = "20") int top) {


        final List<Order> latest = orderService.findLatest(start, top);

        return latest;
    }

    @PostMapping("passOrder")
    @ApiOperation("pass order")
    public String passOrder(@RequestParam("id") Integer id, @RequestParam("state") Integer state) {
        Integer i = orderService.passOrder(id, state);
        return i.toString();
    }


    @PostMapping("refuseOrder")
    @ApiOperation("pass order")
    public String refuseApply(@RequestParam("id") Integer id,
        @RequestParam("state") Integer state,
        @RequestParam("advice") String advice) {
        Integer i = orderService.refuseApply(id, state,advice);
        return i.toString();
    }

    @PostMapping("countOrder")
    @ApiOperation("count order")
    public int countOrder() {
        final int i = orderService.countOrders();
        return i;
    }


    @PostMapping("deleteOrder")
    @ApiOperation("delete order")
    public String deleteOrder(@RequestParam("id") Integer id) {
        final Integer i = orderService.deleteOrder(id);

        return i.toString();
    }

    @PostMapping("queryOrder")
    @ApiOperation("query order")
    public List<Order> queryOrder(
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
            return orderService.findByUsername(username);
        } else if (username.length() != 0 && state != -1 && createTime.length() == 0) {
            System.out.println(2);
            return orderService.findByStateAndUsername(state, username);
        } else if (username.length() != 0 && state == -1 && createTime.length() != 0) {
            System.out.println(3);
            return orderService.findByUsernameAndCreateTime(username, createTime);
        } else if (username.length() == 0 && state != -1 && createTime.length() == 0) {
            System.out.println(4);
            return orderService.findByState(state);
        } else if (username.length() == 0 && state != -1 && createTime.length() != 0) {
            System.out.println(5);
            return orderService.findByStateAndCreateTime(state, createTime);
        } else if (username.length() == 0 && state == -1 && createTime.length() != 0) {
            System.out.println(6);
            return orderService.findByCreateTime(createTime);
        } else {
            System.out.println(7);
            return orderService.findByCreateTimeAndUsernameAndState(username, createTime, state);
        }


    }
}
