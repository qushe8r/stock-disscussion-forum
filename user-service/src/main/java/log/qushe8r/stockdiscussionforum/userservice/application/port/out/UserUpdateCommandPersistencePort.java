package log.qushe8r.stockdiscussionforum.userservice.application.port.out;

import log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence.UserJpaEntity;

public interface UserUpdateCommandPersistencePort {

    void update(UserJpaEntity jpaEntity);

}
