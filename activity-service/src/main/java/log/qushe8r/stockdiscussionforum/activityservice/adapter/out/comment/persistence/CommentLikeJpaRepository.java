package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentLikeJpaRepository extends JpaRepository<CommentLikeJpaEntity, Long> {

    @Modifying
    @Query("DELETE FROM CommentLikeJpaEntity cl WHERE cl.userId = :requestingUserId AND cl.commentJpaEntity.id = :commentId")
    void deleteByUserIdAndCommentJpaEntityId(Long requestingUserId, Long commentId);

}
