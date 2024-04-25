package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

public interface UserUpdateUseCase {

    void userUpdateInformation(Long userId, UserUpdateCommand command);
}
