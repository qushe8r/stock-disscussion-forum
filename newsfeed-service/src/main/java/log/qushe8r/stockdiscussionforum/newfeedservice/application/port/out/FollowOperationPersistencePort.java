package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out;

public interface FollowOperationPersistencePort {

    void follow(Long followerId, Long followeeId);

    void unfollow(Long followerId, Long followeeId);

}
