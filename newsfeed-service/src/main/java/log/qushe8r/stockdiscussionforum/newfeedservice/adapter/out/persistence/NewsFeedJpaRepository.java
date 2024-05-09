package log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsFeedJpaRepository extends JpaRepository<NewsFeedJpaEntity, Long> {

    void deleteByFolloweeIdAndActivityId(Long followeeId, Long activityId);

    List<NewsFeedJpaEntity> findByFollowerId(Long followerId);
}
