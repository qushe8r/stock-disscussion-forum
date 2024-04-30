package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeJpaRepository extends JpaRepository<CommentLikeJpaEntity, Long> {
    void deleteByWriterIdAndCommentJpaEntityId(Long requestingUserId, Long commentId);
}
