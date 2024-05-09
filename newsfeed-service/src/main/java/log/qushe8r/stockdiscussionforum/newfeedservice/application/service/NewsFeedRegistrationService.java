package log.qushe8r.stockdiscussionforum.newfeedservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence.FollowJpaEntity;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedRegistrationCommand;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.FollowQueryPersistencePort;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.NewsFeedRegistrationPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class NewsFeedRegistrationService implements NewsFeedRegistrationUseCase {

    private final NewsFeedRegistrationPersistencePort persistencePort;
    private final FollowQueryPersistencePort followQueryPort;

    public void registerNewsFeed(NewsFeedRegistrationCommand command) {
        List<Long> followerIds = followQueryPort.findByFolloweeId(command.followeeId()).stream()
                .map(FollowJpaEntity::getFollowerId)
                .toList();

        persistencePort.registerNewsFeed(followerIds, command);
    }

}
