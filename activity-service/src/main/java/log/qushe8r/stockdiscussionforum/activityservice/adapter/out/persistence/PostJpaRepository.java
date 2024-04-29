package log.qushe8r.stockdiscussionforum.activityservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {

    List<PostJpaEntity> findByWriterId(Long writerId);

}
