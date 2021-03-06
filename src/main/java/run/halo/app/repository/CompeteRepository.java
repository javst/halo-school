package run.halo.app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import run.halo.app.model.entity.Compete;
import run.halo.app.model.entity.Order;
import run.halo.app.repository.base.BaseRepository;
import java.util.List;

public interface CompeteRepository extends BaseRepository<Compete, Integer> {

    //find lastest
    @Query(value = "select * from compete limit :start , :top", nativeQuery = true)
    public List<Compete> findLatest(@Param("start") Integer start, @Param("top") Integer top);


    @Query(value = "select count(*) from compete", nativeQuery = true)
    public Integer countCompete();

    @Query(value = "select  * from compete where create_time >= :createTime", nativeQuery = true)
    public List<Compete> findByCreateTime(@Param("createTime") String createTime);

    @Query(value = "select  * from compete where username = :username and create_time >= " +
        ":createTime", nativeQuery = true)
    public List<Compete> findByCreateTimeAndUsername(@Param("createTime") String createTime,
        @Param("username") String username);

    @Query(value = "select  * from compete where username = :username", nativeQuery = true)
    public List<Compete> findByUsername(@Param("username") String username);

    @Query("select compete from Compete compete where compete.userId = :userId")
    public List<Compete> findByUserId(@Param("userId") Integer userId);

    //passOrder
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update compete set state = :state where id = :id", nativeQuery = true)
    public Object passApply(@Param("id") Integer id, @Param("state") Integer state);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update compete set state = :state ,advice = :advice where id = :id", nativeQuery = true)
    Object refuseApply(Integer id, Integer state, String advice);
}
