package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.application.port.in.PostRegistrationCommand;
import log.qushe8r.stockdiscussionforum.activityservice.application.port.out.PostRegistrationPersistencePort;
import log.qushe8r.stockdiscussionforum.activityservice.application.service.PostMapper;
import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PostRegistrationPersistenceAdapter implements PostRegistrationPersistencePort {

    private final PostMapper mapper;
    private final PostJpaRepository repository;

    @Override
    public void registerPost(Long writerId, PostRegistrationCommand postRegistrationCommand) {
        PostJpaEntity postJpaEntity = mapper.toJpaEntity(writerId, postRegistrationCommand);
        repository.save(postJpaEntity);
    }

}
