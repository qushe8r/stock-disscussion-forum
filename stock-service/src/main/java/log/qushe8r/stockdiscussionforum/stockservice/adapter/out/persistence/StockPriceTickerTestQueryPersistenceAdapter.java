package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockPriceTickerTestQueryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class StockPriceTickerTestQueryPersistenceAdapter implements StockPriceTickerTestQueryPersistencePort {

    private final StockDailyPriceRecordJpaRepository repository;

    @Override
    public List<StockDailyPriceRecordJpaEntity> findAllQuery() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return repository.findAllQuery(yesterday);
    }

}
