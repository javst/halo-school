package run.halo.app.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import run.halo.app.model.entity.PostComment;
import run.halo.app.model.enums.CommentStatus;
import run.halo.app.model.projection.CommentChildrenCountProjection;
import run.halo.app.model.projection.CommentCountProjection;
import run.halo.app.repository.base.BaseCommentRepository;

/**
 * PostComment repository.
 *
 * @author johnniang
 * @author ryanwang
 * @date 2019-03-21
 */
public interface PostCommentRepository extends BaseCommentRepository<PostComment> {

    /**
     * Count comments by post ids.
     *
     * @param postIds post id collection must not be null
     * @return a list of CommentCountProjection
     */
    @Query(
        "select new run.halo.app.model.projection.CommentCountProjection(count(comment.id), "
            + "comment.postId) "
            + "from PostComment comment "
            + "where comment.postId in ?1 group by comment.postId")
    @NonNull
    @Override
    List<CommentCountProjection> countByPostIds(@NonNull Collection<Integer> postIds);

    /**
     * Counts comment count by comment status and post id collection.
     *
     * @param status status must not be null
     * @param postsId post id collection must not be null
     * @return a list of comment count
     */
    @Query(
        "select new run.halo.app.model.projection.CommentCountProjection(count(comment.id), "
            + "comment.postId) "
            + "from PostComment comment "
            + "where comment.status = ?1 "
            + "and comment.postId in ?2 "
            + "group by comment.postId")
    @NonNull
    @Override
    List<CommentCountProjection> countByStatusAndPostIds(@NonNull CommentStatus status,
        @NonNull Collection<Integer> postsId);

    /**
     * Finds direct children count by comment ids.
     *
     * @param commentIds comment ids must not be null.
     * @return a list of CommentChildrenCountProjection
     */
    @Query(
        "select new run.halo.app.model.projection.CommentChildrenCountProjection(count(comment"
            + ".id), comment.parentId) "
            + "from PostComment comment "
            + "where comment.parentId in ?1 "
            + "group by comment.parentId")
    @NonNull
    @Override
    List<CommentChildrenCountProjection> findDirectChildrenCount(
        @NonNull Collection<Long> commentIds);

    /**
     * ?????????????????????IP????????????????????????
     *
     * @param ipAddress IP??????
     * @param startTime ????????????
     * @param endTime ????????????
     * @return ????????????
     */
    @Query("SELECT COUNT(id) FROM PostComment WHERE ipAddress=?1 AND updateTime BETWEEN ?2 AND ?3"
        + " AND status <> 2")
    int countByIpAndTime(String ipAddress, Date startTime, Date endTime);
}
