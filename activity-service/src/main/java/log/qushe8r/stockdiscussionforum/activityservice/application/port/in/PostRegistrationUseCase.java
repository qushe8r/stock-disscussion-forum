package log.qushe8r.stockdiscussionforum.activityservice.application.port.in;

public interface PostRegistrationUseCase {

    void registerPost(Long userId, PostRegistrationCommand command);

}
