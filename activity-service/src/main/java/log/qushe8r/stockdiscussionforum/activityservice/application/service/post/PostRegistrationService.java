package log.qushe8r.stockdiscussionforum.activityservice.application.service.post;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.post.PostRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence.PostRegistrationPersistencePort;
import log.qushe8r.stockdiscussionforum.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class PostRegistrationService implements PostRegistrationUseCase {

    private final PostRegistrationPersistencePort persistencePort;

    @Transactional
    @Override
    public void registerPost(Long userId, PostRegistrationCommand command) {
        persistencePort.registerPost(userId, command);
    }

}
