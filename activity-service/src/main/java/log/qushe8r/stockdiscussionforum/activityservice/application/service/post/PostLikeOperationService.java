package log.qushe8r.stockdiscussionforum.activityservice.application.service.post;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedCommand;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedServiceClient;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostLikeOperationUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostLikeOperationPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Post;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.PostExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class PostLikeOperationService implements PostLikeOperationUseCase {

    private final PostMapper mapper;
    private final PostQueryPersistencePort queryPort;
    private final PostLikeOperationPersistencePort persistencePort;
    private final NewsFeedServiceClient client;

    @Transactional
    @Override
    public boolean operatePostLike(Long requestingUserId, Long postId) {
        PostJpaEntity postJpaEntity = queryPort.findWithPostLike(postId)
                .orElseThrow(() -> new PostException(PostExceptionCode.POST_NOT_FOUND));

        Post post = mapper.toDomainEntity(requestingUserId, postJpaEntity);
        boolean isLiked = post.togglePostLikeForRequestingUser(
                requestingUserId,
                persistencePort::likePostByRequestingUser,
                persistencePort::unLikePostByRequestingUser);

        NewsFeedCommand newsFeedCommand = isLiked
                ? NewsFeedCommand.registerPostLike(requestingUserId, post.getId())
                : NewsFeedCommand.deletePostLike(requestingUserId, post.getId());

        client.registerNewsfeeds(newsFeedCommand);

        return isLiked;
    }

}
