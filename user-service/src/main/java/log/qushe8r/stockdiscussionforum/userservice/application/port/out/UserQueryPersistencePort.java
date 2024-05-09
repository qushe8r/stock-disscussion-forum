package log.qushe8r.stockdiscussionforum.userservice.application.port.out;

import log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence.UserJpaEntity;

import java.util.List;
import java.util.Optional;

public interface UserQueryPersistencePort {

    Optional<UserJpaEntity> findByUsername(String username);

    Optional<UserJpaEntity> findById(Long userId);

    List<UserJpaEntity> findByIds(List<Long> userIds);

}
