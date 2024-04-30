package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeJpaRepository extends JpaRepository<PostLikeJpaEntity, Long> {

    void deleteByUserIdAndPostJpaEntityId(Long userId, Long postId);

}
