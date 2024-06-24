package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostLikeJpaRepository extends JpaRepository<PostLikeJpaEntity, Long> {

    @Modifying
    @Query("DELETE FROM PostLikeJpaEntity pl WHERE pl.userId = :userId and pl.postJpaEntity.id = :postId")
    void deleteByUserIdAndPostJpaEntityId(Long userId, Long postId);

}
