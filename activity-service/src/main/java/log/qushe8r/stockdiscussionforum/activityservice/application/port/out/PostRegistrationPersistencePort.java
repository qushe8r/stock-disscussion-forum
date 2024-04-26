package log.qushe8r.stockdiscussionforum.activityservice.application.port.out;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationCommand;

public interface PostRegistrationPersistencePort {

    void registerPost(Long writerId, PostRegistrationCommand postRegistrationCommand);

}
