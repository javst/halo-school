package run.halo.app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.User;
import run.halo.app.repository.base.BaseRepository;

/**
 * User repository.
 *
 * @author johnniang
 */
public interface UserRepository extends BaseRepository<User, Integer> {



    //find lastest
    @Query(value = "select * from users limit :start , :top",nativeQuery = true)
    public List<User> findLatest(@Param("start") Integer start , @Param("top") Integer top);


    @Query(value = "select count(*) from users" , nativeQuery = true)
    public Integer countUser();

    @Query(value = "select * from users where nickname = :nickname",nativeQuery = true)
    public List<User> getByNickname(@Param("nickname") String nickname);

    @Query(value = "select * from users where student_num = :student_num",nativeQuery = true)
    public List<User> getByStudent_num(@Param("student_num") String student_num);

    @Query(value = "select * from users where nickname = :nickname and student_num = :student_num",nativeQuery = true)
    public List<User> getByNicknameAndNickname(@Param("nickname") String nickname
        ,@Param("student_num") String student_num);

    /**
     * Gets user by username.
     *
     * @param username username must not be blank
     * @return an optional user
     */
    @NonNull
    Optional<User> findByUsername(@NonNull String username);

    /**
     * Gets user by email.
     *
     * @param email email must not be blank
     * @return an optional user
     */
    @NonNull
    Optional<User> findByEmail(@NonNull String email);
}
