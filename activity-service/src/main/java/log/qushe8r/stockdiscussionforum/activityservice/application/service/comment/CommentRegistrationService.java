package log.qushe8r.stockdiscussionforum.activityservice.application.service.comment;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentRegistrationPersistencePort;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CommentRegistrationService implements CommentRegistrationUseCase {

    private final CommentRegistrationPersistencePort persistencePort;

    @Override
    public void registerComment(Long userId, Long postId, CommentRegistrationCommand command) {
        persistencePort.registerComment(userId, postId, command);
    }

}
