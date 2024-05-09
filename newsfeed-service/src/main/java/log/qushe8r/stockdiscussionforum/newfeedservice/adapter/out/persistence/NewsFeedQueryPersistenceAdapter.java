package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.NewsFeedQueryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class NewsFeedQueryPersistenceAdapter implements NewsFeedQueryPersistencePort {

    private final NewsFeedJpaRepository repository;

    @Override
    public List<NewsFeedJpaEntity> findByFollowerId(Long followerId) {
        return repository.findByFollowerId(followerId);
    }
}
