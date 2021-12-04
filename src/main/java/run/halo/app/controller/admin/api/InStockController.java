package run.halo.app.controller.admin.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.Stock;
import run.halo.app.service.impl.StockServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/api/admin/instock")
public class InStockController {

    private final StockServiceImpl stockService;

    public InStockController(StockServiceImpl stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> getAll() {
        return stockService.findAll();
    }

    @PostMapping("countInStock")
    @ApiOperation("count countInStock")
    public int countOrder() {
        return stockService.countInStock();

    }

    @GetMapping("latest")
    @ApiOperation("Pages latest post")
    public List<Stock> getLatest(@RequestParam(name = "start", defaultValue = "0") int start,
        @RequestParam(name = "top", defaultValue = "20") int top) {
        return stockService.findLatest(start, top);
    }

    @PostMapping("queryOrder")
    @ApiOperation("query order")
    public List<Stock> queryStock(
        @RequestParam(name = "importPeople", defaultValue = "") String importPeople,
        @RequestParam(name = "createTime", defaultValue = "") String createTime) {
        System.out.printf(createTime);
        if (importPeople.length() == 0 && createTime.length() != 0) {
            return stockService.findBy(createTime);
        } else if (importPeople.length() != 0 && createTime.length() == 0) {
            return stockService.findByImportPeople(importPeople);
        } else {
            return stockService.findBy(importPeople, createTime);
        }
    }


}
