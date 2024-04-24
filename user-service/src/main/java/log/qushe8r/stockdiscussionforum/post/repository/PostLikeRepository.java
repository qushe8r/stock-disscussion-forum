package log.qushe8r.stockdiscussionforum.post.repository;

import log.qushe8r.stockdiscussionforum.post.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByUserIdAndPostId(Long userId, Long postId);
}
