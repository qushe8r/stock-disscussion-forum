package log.qushe8r.stockdiscussionforum.stockbatch.stock.repository;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;

import java.util.List;

public interface StockDailyPriceRecordJpaQueryDslRepository {

    List<StockDailyPriceRecordJpaEntity> find26DaysEntities(String itemCode);

    List<StockDailyPriceRecordJpaEntity> findAllByItemCode(String itemCode);

}
