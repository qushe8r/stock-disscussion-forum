package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockDailyPriceRecordJpaEntity;

import java.util.List;

public interface StockPriceTickerTestQueryPersistencePort {

    List<StockDailyPriceRecordJpaEntity> findAllQuery();

}
