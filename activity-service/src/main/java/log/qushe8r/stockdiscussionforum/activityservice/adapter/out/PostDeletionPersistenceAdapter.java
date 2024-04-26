package log.qushe8r.stockdiscussionforum.activityservice.adapter.out;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostDeletionPersistencePort;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostDeletionPersistenceAdapter implements PostDeletionPersistencePort {

    private final PostJpaRepository repository;

    @Override
    public void deleteById(Long postId) {
        repository.deleteById(postId);
    }

}
