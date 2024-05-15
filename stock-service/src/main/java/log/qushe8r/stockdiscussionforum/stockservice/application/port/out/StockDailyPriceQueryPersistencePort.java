package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockDailyPriceRecordJpaEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockDailyPriceQueryPersistencePort {

    List<StockDailyPriceRecordJpaEntity> stockDailyPrice(String itemCode, Pageable pageable);

}
