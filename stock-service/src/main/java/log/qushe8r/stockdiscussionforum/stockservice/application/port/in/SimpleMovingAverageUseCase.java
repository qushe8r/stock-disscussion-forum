package log.qushe8r.stockdiscussionforum.stockservice.application.port.in;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.StockPriceTicker;

import java.util.List;

public interface SimpleMovingAverageUseCase {

    void operate(List<StockPriceTicker> stockPriceTicker);

}
