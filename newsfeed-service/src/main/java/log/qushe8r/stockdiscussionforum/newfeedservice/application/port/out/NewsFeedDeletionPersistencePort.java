package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out;

public interface NewsFeedDeletionPersistencePort {

    void deleteNewsFeeds(Long followeeId, Long activityId);

}
