package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.comment.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.comment.persistence.CommentQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class CommentQueryPersistenceAdapter implements CommentQueryPersistencePort {

    private final CommentJpaRepository repository;

    @Override
    public Optional<CommentJpaEntity> findById(Long commentId) {
        return repository.findById(commentId);
    }
}
