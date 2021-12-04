package run.halo.app.service.impl;

import org.springframework.stereotype.Service;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.Stock;
import run.halo.app.repository.StockRepository;
import run.halo.app.service.StockService;
import java.util.List;
@Service
public class StockServiceImpl implements StockService {

    StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }
    public Stock create(Stock stock){
        return stockRepository.save(stock);
    }

    public List<Stock> findAll(){
        return stockRepository.findAll();
    }

    public Integer countInStock(){
        return stockRepository.countInStock();
    }


    public List<Stock> findBy(String date){
        return stockRepository.findBy(date);
    }


    public List<Stock> findBy(String importPeople,String date){
        return stockRepository.findBy(importPeople,date);
    }
    public List<Stock> findByImportPeople(String importPeople){
        return stockRepository.findByImportPeople(importPeople);
    }

    public List<Stock> findLatest(int start, int top) {
        return stockRepository.findLatest(start,top);
    }
}
