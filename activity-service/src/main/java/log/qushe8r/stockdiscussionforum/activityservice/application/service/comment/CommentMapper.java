package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.CommentResponse;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Comment;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Writer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommentMapper {

    public CommentJpaEntity toJpaEntityPostWithIdOnly(Comment comment) {
        Long postId = comment.getPost().getId();

        return new CommentJpaEntity(
                comment.getId(),
                new PostJpaEntity(postId),
                comment.getContent(),
                comment.getWriter().id(),
                null
        );
    }

    public List<CommentJpaEntity> toJpaEntities(List<Comment> comments) {
        return comments.stream()
                .map(this::toJpaEntityPostWithIdOnly)
                .toList();
    }

    public Comment toDomainEntityWriterNicknameNullPostWithOnlyId(CommentJpaEntity commentJpaEntity) {
        Long writerId = commentJpaEntity.getWriterId();
        Long postId = commentJpaEntity.getPostJpaEntity().getId();

        return Comment.create(
                commentJpaEntity.getId(),
                new Writer(writerId, null),
                Post.createWithIdOnly(postId),
                commentJpaEntity.getContent(),
                null
        );
    }


    public Comment toDomainEntityPostWithIdOnly(CommentJpaEntity commentJpaEntity, Map<Long, String> userIdNickname) {
        Long writerId = commentJpaEntity.getWriterId();
        Long postId = commentJpaEntity.getPostJpaEntity().getId();

        return Comment.create(
                commentJpaEntity.getId(),
                new Writer(writerId, userIdNickname.getOrDefault(writerId, "")),
                Post.createWithIdOnly(postId),
                commentJpaEntity.getContent(),
                null
        );
    }

    public CommentResponse toResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getWriter().name(),
                comment.getContent()
        );
    }

    public List<CommentResponse> toResponses(List<Comment> comments) {
        return comments.stream()
                .map(this::toResponse)
                .toList();
    }

}
