package log.qushe8r.stockdiscussionforum.newfeedservice.application.port.out;

import log.qushe8r.stockdiscussionforum.newfeedservice.adapter.out.persistence.NewsFeedJpaEntity;

import java.util.List;

public interface NewsFeedQueryPersistencePort {

    List<NewsFeedJpaEntity> findByFollowerId(Long followerId);

}
