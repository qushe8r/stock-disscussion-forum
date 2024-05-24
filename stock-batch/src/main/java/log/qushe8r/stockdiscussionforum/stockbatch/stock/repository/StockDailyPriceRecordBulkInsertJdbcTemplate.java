package log.qushe8r.stockdiscussionforum.stockbatch.stock.repository;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;

import java.util.List;

public interface StockDailyPriceRecordBulkInsertJdbcTemplate {

    void bulkInsert(List<StockDailyPriceRecordJpaEntity> stockDailyPriceRecordJpaEntities);

}
