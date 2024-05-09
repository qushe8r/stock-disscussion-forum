package log.qushe8r.stockdiscussionforum.stockbatch.stock.repository;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDailyRecordRepository extends JpaRepository<StockDailyRecord, String> {
}
