package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence.CommentJpaEntity;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentUpdateCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentUpdateUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentUpdatePersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.domain.Comment;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentException;
import log.qushe8r.stockdiscussionforum.activityservice.domain.exception.CommentExceptionCode;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CommentUpdateService implements CommentUpdateUseCase {

    private final CommentMapper mapper;
    private final CommentQueryPersistencePort queryPort;
    private final CommentUpdatePersistencePort persistencePort;

    @Transactional
    @Override
    public void updateComment(Long userId, Long commentId, CommentUpdateCommand command) {
        Comment comment = queryPort.findById(commentId)
                .map(mapper::toDomainEntityWriterNicknameNullPostWithOnlyId)
                .orElseThrow(() -> new CommentException(CommentExceptionCode.COMMENT_NOT_FOUND));

        comment.update(userId, command);

        CommentJpaEntity commentJpaEntity = mapper.toJpaEntityPostWithIdOnly(comment);
        persistencePort.updateComment(commentJpaEntity);
    }

}
