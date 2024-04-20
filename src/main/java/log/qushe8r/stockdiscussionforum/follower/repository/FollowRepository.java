package log.qushe8r.stockdiscussionforum.follower.repository;

import log.qushe8r.stockdiscussionforum.follower.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFolloweeIdAndFollowerId(Long followeeId, Long followerId);

    List<Follow> findByFolloweeId(Long followeeId);
}
