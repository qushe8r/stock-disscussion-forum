package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostRegistrationCommand;

public interface PostRegistrationPersistencePort {

    Long registerPost(Long writerId, PostRegistrationCommand postRegistrationCommand);

}
