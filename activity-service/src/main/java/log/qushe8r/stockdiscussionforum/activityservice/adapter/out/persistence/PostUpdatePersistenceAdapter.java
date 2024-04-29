package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostUpdatePersistencePort;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostUpdatePersistenceAdapter implements PostUpdatePersistencePort {

    private final PostJpaRepository repository;

    @Override
    public void updatePost(PostJpaEntity postJpaEntity) {
        repository.save(postJpaEntity);
    }

}
