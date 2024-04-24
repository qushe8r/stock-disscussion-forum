package log.qushe8r.stockdiscussionforum.post.repository;

import log.qushe8r.stockdiscussionforum.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    void deleteByWriterIdAndId(Long writerId, Long postId);
}
