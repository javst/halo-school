package run.halo.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import run.halo.app.model.entity.Option;
import run.halo.app.model.entity.Order;
import run.halo.app.repository.base.BaseRepository;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order,Integer> {

//find lastest
    @Query(value = "select * from orders limit :top",nativeQuery = true)
    public List<Order> findLatest(@Param("top") Integer top);
    //passOrder
    @Query(value = "update orders set state = 1 where id = 1;##:id :state ", nativeQuery = true)
    public Object passOrder(@Param("id") Integer id,@Param("state") Integer state);




}
