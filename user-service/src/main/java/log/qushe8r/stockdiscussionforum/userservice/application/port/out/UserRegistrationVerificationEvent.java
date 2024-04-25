package log.qushe8r.stockdiscussionforum.userservice.application.port.out;

public record UserRegistrationVerificationEvent(Long userId, String username, String verificationCode) {
}
