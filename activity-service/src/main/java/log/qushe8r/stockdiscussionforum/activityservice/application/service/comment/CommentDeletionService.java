package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentDeletionUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentDeletionPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class CommentDeletionService implements CommentDeletionUseCase {

    private final CommentQueryPersistencePort queryPort;
    private final CommentDeletionPersistencePort persistencePort;

    @Transactional
    @Override
    public void deleteComment(Long userId, Long commentId) {
        Optional<CommentJpaEntity> optionalCommentJapEntity = queryPort.findById(commentId);

        if (optionalCommentJapEntity.isEmpty()) {
            return;
        }

        Long writerId = optionalCommentJapEntity.get().getWriterId();
        if (!writerId.equals(userId)) {
            throw new CommentException(CommentExceptionCode.CANNOT_CHANGE_INFORMATION);
        }

        persistencePort.deleteByCommentId(commentId);
    }

}
