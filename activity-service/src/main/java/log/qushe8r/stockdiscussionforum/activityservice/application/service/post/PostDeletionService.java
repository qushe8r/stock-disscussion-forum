package log.qushe8r.stockdiscussionforum.activityservice.application.service.post;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedCommand;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedServiceClient;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostDeletionUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostDeletionPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class PostDeletionService implements PostDeletionUseCase {

    private final PostMapper mapper;
    private final PostQueryPersistencePort queryPort;
    private final PostDeletionPersistencePort persistencePort;
    private final NewsFeedServiceClient client;

    @Transactional
    @Override
    public void deletePost(Long requestingUserId, Long postId) {
        queryPort.findById(postId).ifPresent(postJpaEntity -> {
            Post post = mapper.toDomainEntityWriterNicknameNull(postJpaEntity);
            post.delete(requestingUserId, persistencePort::deleteById);
        });
        client.registerNewsfeeds(NewsFeedCommand.deletePost(requestingUserId, postId));
    }

}
