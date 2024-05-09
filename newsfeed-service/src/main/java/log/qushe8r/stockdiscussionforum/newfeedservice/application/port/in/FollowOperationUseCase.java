package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.in;

public interface FollowOperationUseCase {

    boolean operateFollow(Long requestingUserId, Long followeeId);

}
