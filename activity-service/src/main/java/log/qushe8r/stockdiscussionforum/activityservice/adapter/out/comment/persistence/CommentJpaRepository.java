package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentJpaRepository extends JpaRepository<CommentJpaEntity, Long> {

	@Query("SELECT c FROM CommentJpaEntity c LEFT JOIN FETCH c.commentLikeJpaEntities WHERE c.id = :commentId")
	Optional<CommentJpaEntity> findByIdWithCommentLike(Long commentId);

}
