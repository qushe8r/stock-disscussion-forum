package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedCommand;
import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.newsfeed.web.NewsFeedServiceClient;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentDeletionUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentDeletionPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Comment;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class CommentDeletionService implements CommentDeletionUseCase {

    private final CommentQueryPersistencePort queryPort;
    private final CommentMapper mapper;
    private final CommentDeletionPersistencePort persistencePort;
    private final NewsFeedServiceClient client;

    @Transactional
    @Override
    public void deleteComment(Long requestingUserId, Long commentId) {
        Optional<CommentJpaEntity> optionalCommentJapEntity = queryPort.findById(commentId);

        optionalCommentJapEntity.map(mapper::toDomainEntityWriterNicknameNullPostWithOnlyId)
                .ifPresent(comment -> deleteComment(requestingUserId, comment));
    }

    private void deleteComment(Long requestingUserId, Comment comment) {
        Long postId = comment.getPost().getId();
        Long commentId = comment.getId();
        comment.delete(requestingUserId, persistencePort::deleteByCommentId);
        client.registerNewsfeeds(NewsFeedCommand.deleteComment(requestingUserId, commentId, postId));
    }

}
