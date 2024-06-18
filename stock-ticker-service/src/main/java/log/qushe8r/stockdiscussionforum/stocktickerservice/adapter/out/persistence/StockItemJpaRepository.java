package log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemJpaRepository extends JpaRepository<StockItemJpaEntity, Long>, StockItemQueryDslJpaRepository {
}
