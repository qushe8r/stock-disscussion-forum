package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentUpdatePersistencePort;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentUpdatePersistenceAdapter implements CommentUpdatePersistencePort {

    private final CommentJpaRepository repository;

    @Override
    public void updateComment(CommentJpaEntity commentJpaEntity) {
        repository.save(commentJpaEntity);
    }

}
