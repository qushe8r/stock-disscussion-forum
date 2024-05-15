package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockDailyPriceQueryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class StockDailyPriceQueryPersistenceAdapter implements StockDailyPriceQueryPersistencePort {

    private final StockDailyPriceRecordJpaRepository repository;

    @Override
    public List<StockDailyPriceRecordJpaEntity> stockDailyPrice(String itemCode, Pageable pageable) {
        return repository.query(itemCode, pageable);
    }

}
