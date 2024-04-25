package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

public interface UserDeletionUseCase {

    void deleteUser(Long requestingUserId, Long requestedUserId);

}
