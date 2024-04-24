package log.qushe8r.stockdiscussionforum.comment.repository;

import log.qushe8r.stockdiscussionforum.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteByWriterIdAndId(Long writerId, Long commentId);
}
