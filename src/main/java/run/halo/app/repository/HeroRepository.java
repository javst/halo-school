package run.halo.app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import run.halo.app.model.entity.Hero;
import run.halo.app.repository.base.BaseRepository;

import java.util.List;

public interface HeroRepository extends BaseRepository<Hero, Integer> {

    //find lastest
    @Query(value = "select * from hero limit :start , :top", nativeQuery = true)
    public List<Hero> findLatest(@Param("start") Integer start, @Param("top") Integer top);


    //passOrder
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update hero set state = :state where id = :id", nativeQuery = true)
    public Object passHero(@Param("id") Integer id, @Param("state") Integer state);



    //deleteOrder
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from hero where id = :id", nativeQuery = true)
    public Integer deleteHero(@Param("id") Integer id);


    @Query(value = "select count(*) from hero", nativeQuery = true)
    public Integer countHero();

    @Query(value = "select  * from hero where state = :state", nativeQuery = true)
    public List<Hero> findByState(@Param("state") int state);

    @Query(value = "select  * from hero where username = :username order by id desc ", nativeQuery = true)
    public List<Hero> findByUsername(@Param("username") String username);

    @Query(value = "select  * from hero where create_time >= :createTime", nativeQuery = true)
    public List<Hero> findByCreateTime(@Param("createTime") String createTime);

    @Query(value = "select  * from hero where state = :state and username = :username",
        nativeQuery = true)
    public List<Hero> findByStateAndUsername(@Param("username") String username,
        @Param("state") int state);

    @Query(value = "select  * from hero where state = :state and create_time >= :createTime",
        nativeQuery = true)
    public List<Hero> findByStateAndCreateTime(@Param("createTime") String createTime,
        @Param("state") int state);

    @Query(value = "select  * from hero where username = :username and create_time >= " +
        ":createTime", nativeQuery = true)
    public List<Hero> findByCreateTimeAndUsername(@Param("createTime") String createTime,
        @Param("username") String username);

    @Query(value = "select  * from hero where username = :username and create_time >= " +
        ":createTime and state = :state", nativeQuery = true)
    public List<Hero> findByCreateTimeAndUsernameAndState(@Param("createTime") String createTime,
        @Param("username") String username, @Param("state") int state);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update hero set state = :state ,advice = :advice where id = :id", nativeQuery = true)
    Object refuseApply(Integer id, Integer state, String advice);

    @Query(value = "select  * from hero where user_id = :id order by id desc ", nativeQuery = true)
    List<Hero> findByUserId(Integer id);
}
