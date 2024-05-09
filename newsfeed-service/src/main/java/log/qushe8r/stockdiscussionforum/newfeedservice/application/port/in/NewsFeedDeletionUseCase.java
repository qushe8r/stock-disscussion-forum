package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in;

public interface NewsFeedDeletionUseCase {

    void deleteNewsFeeds(Long followeeId, Long activityId);

}
