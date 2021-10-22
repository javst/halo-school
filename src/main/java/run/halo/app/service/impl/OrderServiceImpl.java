package run.halo.app.service.impl;

import org.springframework.stereotype.Service;
import run.halo.app.model.entity.Order;
import run.halo.app.repository.OrderRepository;
import run.halo.app.repository.base.BaseRepository;
import run.halo.app.service.OrderService;
import run.halo.app.service.base.AbstractCrudService;

@Service
public class OrderServiceImpl extends AbstractCrudService<Order,Integer> implements OrderService {


    private OrderRepository orderRepository;

    protected OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }


}
