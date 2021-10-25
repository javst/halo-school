package run.halo.app.repository;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import run.halo.app.model.entity.Option;
import run.halo.app.model.entity.Order;
import run.halo.app.repository.base.BaseRepository;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order,Integer> {

//find lastest
    @Query(value = "select * from orders limit :top",nativeQuery = true)
    public List<Order> findLatest(@Param("top") Integer top);


    //passOrder
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update orders set state = :state where id = :id", nativeQuery = true)
    public Object passOrder(@Param("id") Integer id,@Param("state") Integer state);

    //deleteOrder
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from orders where id = :id", nativeQuery = true)
    public Integer deleteOrder(@Param("id") Integer id);







}
