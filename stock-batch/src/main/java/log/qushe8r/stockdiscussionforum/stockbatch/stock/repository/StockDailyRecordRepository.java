package log.qushe8r.stockdiscussionforum.stockbatch.stock.repository;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDailyRecordRepository extends JpaRepository<StockDailyPriceRecordJpaEntity, String> {
}
