package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.NewsFeedDeletionPersistencePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class NewsFeedDeletionPersistenceAdapter implements NewsFeedDeletionPersistencePort {

    private final NewsFeedJpaRepository repository;

    @Override
    public void deleteNewsFeeds(Long followeeId, Long activityId) {
        repository.deleteByFolloweeIdAndActivityId(followeeId, activityId);
    }

}
