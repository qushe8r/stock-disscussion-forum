package log.qushe8r.stockdiscussionforum.activityservice.application.service;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Writer;
import org.springframework.stereotype.Component;

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

    public PostJpaEntity toJpaEntity(Post post) {
        return new PostJpaEntity(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter().id()
        );
    }
}
