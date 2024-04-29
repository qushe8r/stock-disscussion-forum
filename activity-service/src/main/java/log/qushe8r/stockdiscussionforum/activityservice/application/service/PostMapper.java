package log.qushe8r.stockdiscussionforum.activityservice.application.service;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.persistence.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.web.UserResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostDetailsResponse;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostResponse;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Writer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class PostMapper {
    public PostJpaEntity toJpaEntity(Long writerId, PostRegistrationCommand postRegistrationCommand) {
        String title = postRegistrationCommand.getTitle();
        String content = postRegistrationCommand.getContent();

        return new PostJpaEntity(title, content, writerId);
    }

    public Post toDomainEntity(PostJpaEntity postJpaEntity) {
        Long postId = postJpaEntity.getId();
        String title = postJpaEntity.getTitle();
        String content = postJpaEntity.getContent();
        Long writerId = postJpaEntity.getWriterId();
        Writer writer = new Writer(writerId, null);

        return Post.create(postId, title, content, writer, null);
    }

    public Post toDomainEntity(PostJpaEntity postJpaEntity, String name) {
        Long postId = postJpaEntity.getId();
        String title = postJpaEntity.getTitle();
        String content = postJpaEntity.getContent();
        Long writerId = postJpaEntity.getWriterId();
        Writer writer = new Writer(writerId, name);

        return Post.create(postId, title, content, writer, null);
    }


    public PostJpaEntity toJpaEntity(Post post) {
        return new PostJpaEntity(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter().id()
        );
    }

    public PostDetailsResponse toResponseDetails(Post post) {
        return new PostDetailsResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter().name()
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
