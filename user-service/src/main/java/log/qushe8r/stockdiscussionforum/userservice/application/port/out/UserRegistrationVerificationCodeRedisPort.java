package log.qushe8r.stockdiscussionforum.userservice.application.port.out;

public interface UserRegistrationVerificationCodeRedisPort {

    void saveVerificationCode(Long userId, String code);

    String getVerificationCode(Long userId);

    void removeVerificationCode(Long userId);

}
