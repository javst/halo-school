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

public interface OrderRepository extends BaseRepository<Order, Integer> {

    //find lastest
    @Query(value = "select * from orders limit :start , :top", nativeQuery = true)
    public List<Order> findLatest(@Param("start") Integer start, @Param("top") Integer top);


    //passOrder
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update orders set state = :state where id = :id", nativeQuery = true)
    public Object passOrder(@Param("id") Integer id, @Param("state") Integer state);

    //deleteOrder
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from orders where id = :id", nativeQuery = true)
    public Integer deleteOrder(@Param("id") Integer id);


    @Query(value = "select count(*) from orders", nativeQuery = true)
    public Integer countOrder();

    @Query(value = "select  * from orders where state = :state", nativeQuery = true)
    public List<Order> findByState(@Param("state") int state);

    @Query(value = "select  * from orders where username = :username order by id desc ", nativeQuery = true)
    public List<Order> findByUsername(@Param("username") String username);

    @Query(value = "select  * from orders where create_time >= :createTime", nativeQuery = true)
    public List<Order> findByCreateTime(@Param("createTime") String createTime);

    @Query(value = "select  * from orders where state = :state and username = :username",
        nativeQuery = true)
    public List<Order> findByStateAndUsername(@Param("username") String username,
        @Param("state") int state);

    @Query(value = "select  * from orders where state = :state and create_time >= :createTime",
        nativeQuery = true)
    public List<Order> findByStateAndCreateTime(@Param("createTime") String createTime,
        @Param("state") int state);

    @Query(value = "select  * from orders where username = :username and create_time >= " +
        ":createTime", nativeQuery = true)
    public List<Order> findByCreateTimeAndUsername(@Param("createTime") String createTime,
        @Param("username") String username);

    @Query(value = "select  * from orders where username = :username and create_time >= " +
        ":createTime and state = :state", nativeQuery = true)
    public List<Order> findByCreateTimeAndUsernameAndState(@Param("createTime") String createTime,
        @Param("username") String username, @Param("state") int state);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update orders set state = :state ,advice = :advice where id = :id", nativeQuery = true)
    Object refuseApply(Integer id, Integer state, String advice);
}
