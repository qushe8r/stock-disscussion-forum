package log.qushe8r.stockdiscussionforum.userservice.application.port.in;

public interface UserRegistrationUseCase {

    void registerUser(UserRegistrationCommand command);

    String verifyCode(Long userId, String code);

}
