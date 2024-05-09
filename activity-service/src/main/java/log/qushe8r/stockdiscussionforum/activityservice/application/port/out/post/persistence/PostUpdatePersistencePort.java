package log.qushe8r.stockdiscussionforum.activityservice.application.port.out.post.persistence;

import log.qushe8r.stockdiscussionforum.activityservice.adapter.out.post.persistence.PostJpaEntity;

public interface PostUpdatePersistencePort {

    void updatePost(PostJpaEntity postJpaEntity);

}
