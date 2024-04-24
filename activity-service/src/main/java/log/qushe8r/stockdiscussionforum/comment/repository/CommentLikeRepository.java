package log.qushe8r.stockdiscussionforum.comment.repository;

import log.qushe8r.stockdiscussionforum.comment.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    @Query("""
            SELECT commentLike FROM CommentLike commentLike
            WHERE commentLike.user.id = :userId AND commentLike.comment.id = :commentId
            """)
    Optional<CommentLike> findByUserIdAndCommentId(Long userId, Long commentId);
}
