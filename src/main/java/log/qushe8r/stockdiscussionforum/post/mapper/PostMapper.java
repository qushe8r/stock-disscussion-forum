package log.qushe8r.stockdiscussionforum.post.mapper;

import log.qushe8r.stockdiscussionforum.comment.dto.CommentResponse;
import log.qushe8r.stockdiscussionforum.comment.mapper.CommentMapper;
import log.qushe8r.stockdiscussionforum.post.dto.PostCreateRequest;
import log.qushe8r.stockdiscussionforum.post.dto.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.post.dto.PostResponse;
import log.qushe8r.stockdiscussionforum.post.entity.Post;
import log.qushe8r.stockdiscussionforum.security.user.AuthenticatedUser;
import log.qushe8r.stockdiscussionforum.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final CommentMapper commentMapper;

    public Post toEntity(Long userId, PostCreateRequest request) {
        return new Post(request.title(), request.content(), new User(userId));
    }

    public PostDetailsResponse toDetailsResponse(Post post) {
        return new PostDetailsResponse(post.getId(), post.getTitle(), post.getContent(), toCommentsResponse(post));
    }

    public PostResponse toResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getWriter().getNickname());
    }

    private List<CommentResponse> toCommentsResponse(Post post) {
        return post.getComments().stream().map(commentMapper::toResponse).toList();
    }
}
