package log.qushe8r.stockdiscussionforum.post.mapper;

import log.qushe8r.stockdiscussionforum.post.dto.PostCreateRequest;
import log.qushe8r.stockdiscussionforum.post.dto.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.post.dto.PostResponse;
import log.qushe8r.stockdiscussionforum.post.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post toEntity(PostCreateRequest request) {
        return new Post(request.title(), request.content());
    }

    public PostDetailsResponse toDetailsResponse(Post post) {
        return new PostDetailsResponse(post.getId(), post.getTitle(), post.getContent());
    }

    public PostResponse toResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent());
    }
}
