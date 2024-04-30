package log.qushe8r.stockdiscussionforum.activityservice.application.service.post;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.service.comment.CommentMapper;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Comment;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Writer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final CommentMapper commentMapper;

    public PostJpaEntity toJpaEntity(Long writerId, PostRegistrationCommand postRegistrationCommand) {
        String title = postRegistrationCommand.getTitle();
        String content = postRegistrationCommand.getContent();

        return new PostJpaEntity(title, content, writerId);
    }

    public Post toDomainEntityWriterNicknameNull(PostJpaEntity postJpaEntity) {
        Long postId = postJpaEntity.getId();
        String title = postJpaEntity.getTitle();
        String content = postJpaEntity.getContent();
        Long writerId = postJpaEntity.getWriterId();
        Writer writer = new Writer(writerId, null);

        return Post.create(postId, title, content, writer, null, null);
    }

    public Post toDomainEntity(PostJpaEntity postJpaEntity, Map<Long, String> writerIdNickname) {
        Long postId = postJpaEntity.getId();
        String title = postJpaEntity.getTitle();
        String content = postJpaEntity.getContent();
        Long writerId = postJpaEntity.getWriterId();
        Writer writer = new Writer(writerId, writerIdNickname.get(writerId));
        List<Comment> comments = postJpaEntity.getCommentJpaEntities().stream()
                .map(commentJpaEntity -> commentMapper.toDomainEntityPostWithIdOnly(commentJpaEntity, writerIdNickname))
                .toList();

        return Post.create(postId, title, content, writer, comments, null);
    }

    public PostJpaEntity toJpaEntity(Post post) {
        return new PostJpaEntity(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter().id(),
                commentMapper.toJpaEntities(post.getComments())
        );
    }

    public PostDetailsResponse toResponseDetails(Post post) {
        return new PostDetailsResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter().name(),
                commentMapper.toResponses(post.getComments())
        );
    }

    public PostResponse toResponse(PostJpaEntity postJpaEntity, String writer) {
        return new PostResponse(
                postJpaEntity.getId(),
                postJpaEntity.getTitle(),
                postJpaEntity.getContent(),
                writer
        );
    }

    public List<PostResponse> toResponses(List<PostJpaEntity> postJpaEntities, Map<Long, String> userNickname) {
        return postJpaEntities.stream()
                .map(postJpaEntity -> toResponse(postJpaEntity, userNickname.get(postJpaEntity.getWriterId())))
                .toList();
    }

}
