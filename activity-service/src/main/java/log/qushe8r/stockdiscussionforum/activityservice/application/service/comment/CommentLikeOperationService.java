package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedCommand;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedServiceClient;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentLikeOperationUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentLikeOperationPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Comment;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CommentLikeOperationService implements CommentLikeOperationUseCase {

    private final CommentMapper mapper;
    private final CommentQueryPersistencePort queryPort;
    private final CommentLikeOperationPersistencePort persistencePort;
    private final NewsFeedServiceClient client;

    @Transactional
    @Override
    public boolean operateCommentLike(Long requestingUserId, Long commentId) {
        CommentJpaEntity commentJpaEntity = queryPort.findByIdWithCommentLike(commentId)
                .orElseThrow(() -> new CommentException(CommentExceptionCode.COMMENT_NOT_FOUND));

        Comment comment = mapper.toDomainEntityWithCommentLikeOnly(requestingUserId, commentJpaEntity);

        boolean isLiked = comment.toggleCommentLikeForRequestingUser(
                requestingUserId,
                persistencePort::likePostByRequestingUser,
                persistencePort::unLikePostByRequestingUser);

        Long postId = comment.getPost().getId();
        if (isLiked) {
            client.registerNewsfeeds(NewsFeedCommand.registerCommentLike(requestingUserId, commentId, postId));
        } else {
            client.registerNewsfeeds(NewsFeedCommand.commentLikeDelete(requestingUserId, commentId, postId));
        }

        return isLiked;
    }

}
