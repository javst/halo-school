package run.halo.app.controller.admin.api;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.entity.Order;
import run.halo.app.repository.OrderRepository;
import run.halo.app.service.impl.OrderServiceImpl;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class OrderController {

    private  final  OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService){
        this.orderService = orderService;

    }

    @GetMapping("latest")
    @ApiOperation("Pages latest post")
    public List<Order> getLatest(@RequestParam(name = "top",defaultValue = "100") int top ){


        final List<Order> latest = orderService.findLatest(top);

        return latest;
    }

    @PostMapping("passOrder")
    @ApiOperation("pass order")
    public String passOrder(@RequestParam("id") Integer id,@RequestParam("state") Integer state){
         Integer i = orderService.passOrder(id, state);
        return i.toString();
    }

    @PostMapping("deleteOrder")
    @ApiOperation("delete order")
    public String deleteOrder(@RequestParam("id") Integer id){
        final Integer i = orderService.deleteOrder(id);

        return i.toString();
    }
}
