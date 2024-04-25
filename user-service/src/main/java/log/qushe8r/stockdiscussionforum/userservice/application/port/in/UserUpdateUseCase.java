package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

public interface UserUpdateUseCase {

    void updateUserInformation(Long userId, UserUpdateCommand command);

    void updateUserPassword(Long userId, UserPasswordUpdateCommand command);

}
