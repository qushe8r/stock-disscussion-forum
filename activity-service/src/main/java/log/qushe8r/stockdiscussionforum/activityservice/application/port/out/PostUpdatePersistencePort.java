package log.qushe8r.stockdiscussionforum.activityservice.application.port.out;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.PostJpaEntity;

public interface PostUpdatePersistencePort {

    void updatePost(PostJpaEntity postJpaEntity);

}
