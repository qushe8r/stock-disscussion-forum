package log.qushe8r.stockdiscussionforum.userservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByUsername(String username);

    @Query("SELECT u FROM UserJpaEntity u WHERE u.id IN (:userIds)")
    List<UserJpaEntity> findByIds(List<Long> userIds);

}
