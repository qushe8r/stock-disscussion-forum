package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockDailyPriceQueryUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockDailyPriceResponse;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockDailyPriceQueryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class StockDailyPriceQueryService implements StockDailyPriceQueryUseCase {

    private final StockDailyPriceQueryPersistencePort persistencePort;
    private final StockDailyPriceMapper mapper;

    @Override
    public List<StockDailyPriceResponse> stockDailyPrice(String itemCode, Pageable pageable) {
        return persistencePort.stockDailyPrice(itemCode, pageable).stream()
                .map(mapper::toStockDailyPriceResponse)
                .toList();
    }
}
