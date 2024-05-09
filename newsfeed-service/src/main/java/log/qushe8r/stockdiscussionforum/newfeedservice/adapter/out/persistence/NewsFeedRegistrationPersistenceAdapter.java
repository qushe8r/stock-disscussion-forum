package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedRegistrationCommand;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.NewsFeedRegistrationPersistencePort;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.service.NewsFeedMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class NewsFeedRegistrationPersistenceAdapter implements NewsFeedRegistrationPersistencePort {

    private final NewsFeedMapper mapper;
    private final NewsFeedJpaRepository repository;

    @Override
    public void registerNewsFeed(List<Long> followerIds, NewsFeedRegistrationCommand command) {
        List<NewsFeedJpaEntity> newsFeedJpaEntities = followerIds.stream()
                .map(followerId -> mapper.toJpaEntity(followerId, command))
                .toList();
        repository.saveAll(newsFeedJpaEntities);
    }
}
