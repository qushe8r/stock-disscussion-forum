package log.qushe8r.stockdiscussionforum.newfeedservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in.NewsFeedDeletionUseCase;
import log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out.NewsFeedDeletionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class NewsFeedDeletionService implements NewsFeedDeletionUseCase {

    private final NewsFeedDeletionPersistencePort persistencePort;

    @Transactional
    @Override
    public void deleteNewsFeeds(Long followeeId, Long activityId) {
        persistencePort.deleteNewsFeeds(followeeId, activityId);
    }
}
