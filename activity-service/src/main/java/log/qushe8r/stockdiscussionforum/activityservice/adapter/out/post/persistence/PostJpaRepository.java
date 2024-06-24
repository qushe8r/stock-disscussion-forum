package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {

	List<PostJpaEntity> findByWriterId(Long writerId);

	@Query("SELECT p FROM PostJpaEntity p LEFT JOIN FETCH p.postLikeJpaEntities where p.id = :postId")
	Optional<PostJpaEntity> findWithPostLike(Long postId);

}
