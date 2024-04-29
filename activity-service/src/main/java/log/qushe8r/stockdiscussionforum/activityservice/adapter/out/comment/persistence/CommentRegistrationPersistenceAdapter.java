package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.comment.CommentRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentRegistrationPersistencePort;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentRegistrationPersistenceAdapter implements CommentRegistrationPersistencePort {

    private final CommentJpaRepository repository;

    @Override
    public void registerComment(Long userId, Long postId, CommentRegistrationCommand command) {
        CommentJpaEntity commentJpaEntity = new CommentJpaEntity(postId, command.getContent(), userId);
        repository.save(commentJpaEntity);
    }

}
