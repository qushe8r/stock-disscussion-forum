package log.qushe8r.stockdiscussionforum.stockservice.application.port.in;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockDailyPriceQueryUseCase {

    List<StockDailyPriceResponse> stockDailyPrice(String itemCode, Pageable pageable);

}
