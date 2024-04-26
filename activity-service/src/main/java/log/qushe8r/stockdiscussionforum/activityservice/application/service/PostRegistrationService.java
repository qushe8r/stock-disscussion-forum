package log.qushe8r.stockdiscussionforum.activityservice.application.service;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationUseCase;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostRegistrationPersistencePort;
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
