package log.qushe8r.stockdiscussionforum.stockbatch.stock.repository;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockItemRepository extends JpaRepository<StockItemJpaEntity, String> {
}
