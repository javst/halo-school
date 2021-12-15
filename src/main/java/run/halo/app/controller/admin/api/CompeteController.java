package run.halo.app.controller.admin.api;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import run.halo.app.model.entity.Compete;
import run.halo.app.model.entity.Order;
import run.halo.app.service.impl.CompeteServiceImpl;
import run.halo.app.service.impl.OrderServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/api/admin/compete")
public class CompeteController {

    private final CompeteServiceImpl competeService;

    public CompeteController(CompeteServiceImpl competeService) {
        this.competeService = competeService;

    }

    @GetMapping("latest")
    @ApiOperation("Pages latest post")
    public List<Compete> getLatest(@RequestParam(name = "start", defaultValue = "0") int start,
        @RequestParam(name = "top", defaultValue = "20") int top) {


        final List<Compete> latest = competeService.findLatest(start, top);

        return latest;
    }



    @PostMapping("countCompete")
    @ApiOperation("count order")
    public int countOrder() {
        final int i = competeService.countOrders();
        return i;
    }



    @PostMapping("queryCompete")
    @ApiOperation("query order")
    public List<Compete> queryOrder(
        @RequestParam(name = "username", defaultValue = "") String username,
        @RequestParam(name = "createTime", defaultValue = "") String createTime) {
        if (username.length() == 0  && createTime.length() == 0) {
            return null;
        } else if (username.length() != 0 && createTime.length() == 0) {
            return competeService.findByUsername(username);
        } else if (username.length() != 0  && createTime.length() != 0) {
            return competeService.findByUsernameAndCreateTime(username, createTime);
        } else if (username.length() == 0  && createTime.length() != 0) {
            return competeService.findByCreateTime(createTime);
        }
        return null;
    }
}
