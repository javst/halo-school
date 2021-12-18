package run.halo.app.service.impl;

import org.apache.commons.logging.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import run.halo.app.model.entity.Order;
import run.halo.app.repository.OrderRepository;
import run.halo.app.repository.base.BaseRepository;
import run.halo.app.service.OrderService;
import run.halo.app.service.base.AbstractCrudService;
import java.util.List;

@Service
public class OrderServiceImpl extends AbstractCrudService<Order, Integer> implements OrderService {


    private OrderRepository orderRepository;

    protected OrderServiceImpl(OrderRepository orderRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
    }


    public List<Order> findLatest(int start, int top) {
        final List<Order> latest = orderRepository.findLatest(start, top);
        System.out.println(latest);
        return latest;
    }

    public List<Order> findByState(int state) {
        final List<Order> byState = orderRepository.findByState(state);
        return byState;
    }


    public int passOrder(Integer id, Integer state) {
        final Object i = orderRepository.passOrder(id, state);
        if ((Integer) i > 0) {
            return 1;
        }
        return 0;

    }

    public int deleteOrder(Integer id) {
        final Object i = orderRepository.deleteOrder(id);
        if ((Integer) i > 0) {
            return 1;
        }
        return 0;

    }

    public int countOrders() {
        final Integer count = orderRepository.countOrder();
        return count;
    }


    public List<Order> findByCreateTime(String createTime) {
        return orderRepository.findByCreateTime(createTime);
    }

    public List<Order> findByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public List<Order> findByUsernameAndCreateTime(String username, String createTime) {
        return orderRepository.findByCreateTimeAndUsername(createTime, username);
    }

    public List<Order> findByStateAndUsername(int state, String username) {
        return orderRepository.findByStateAndUsername(username, state);
    }

    public List<Order> findByStateAndCreateTime(int state, String createTime) {
        return orderRepository.findByStateAndCreateTime(createTime, state);
    }

    public List<Order> findByCreateTimeAndUsernameAndState(String username, String createTime,
        int state) {
        return orderRepository.findByCreateTimeAndUsernameAndState(createTime, username, state);
    }


    public Integer refuseApply(Integer id, Integer state, String advice) {
        final Object o = orderRepository.refuseApply(id, state, advice);
        if ((Integer) o > 0) {
            return 1;
        }
        return 0;
    }
}
