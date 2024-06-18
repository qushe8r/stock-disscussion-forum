package log.qushe8r.stockdiscussionforum.stockbatch.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;

public interface StockDailyPriceRecordJpaRepository
	extends JpaRepository<StockDailyPriceRecordJpaEntity, Long>, StockDailyPriceRecordJpaQueryDslRepository,
	StockDailyPriceRecordBulkInsertJdbcTemplate {
}
