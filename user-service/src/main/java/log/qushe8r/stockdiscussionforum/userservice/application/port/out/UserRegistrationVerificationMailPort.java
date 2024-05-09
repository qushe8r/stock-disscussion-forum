package log.qushe8r.stockdiscussionforum.userservice.application.port.out;

public interface UserRegistrationVerificationMailPort {

    void send(UserRegistrationVerificationEvent event);

}
