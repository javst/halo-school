package run.halo.app.service.impl;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;
import run.halo.app.model.entity.Order;
import run.halo.app.repository.OrderRepository;
import run.halo.app.repository.base.BaseRepository;
import run.halo.app.service.OrderService;
import run.halo.app.service.base.AbstractCrudService;
import java.util.List;

@Service
public class OrderServiceImpl extends AbstractCrudService<Order,Integer> implements OrderService {


    private OrderRepository orderRepository;

    protected OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }


    public List<Order> findLatest(int top){
        final List<Order> latest = orderRepository.findLatest(top);
        System.out.println(latest);
        return latest;
    }

    public int passOrder(Integer id,Integer state){
        final Object i = orderRepository.passOrder(id, state);
        if((Integer)i>0){
            return 1;
        }
        return 0;

    }


}
