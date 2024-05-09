package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowJpaRepository extends JpaRepository<FollowJpaEntity, Long> {

    Optional<FollowJpaEntity> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    void deleteByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    List<FollowJpaEntity> findByFolloweeId(Long followeeId);

}
