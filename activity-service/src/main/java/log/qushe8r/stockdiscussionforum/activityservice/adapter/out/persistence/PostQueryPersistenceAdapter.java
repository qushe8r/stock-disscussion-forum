package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostQueryPersistenceAdapter implements PostQueryPersistencePort {

    private final PostJpaRepository repository;

    @Override
    public Optional<PostJpaEntity> findById(Long postId) {
        return repository.findById(postId);
    }

    @Override
    public List<PostJpaEntity> findByWriterId(Long writerId) {
        return repository.findByWriterId(writerId);
    }

}
