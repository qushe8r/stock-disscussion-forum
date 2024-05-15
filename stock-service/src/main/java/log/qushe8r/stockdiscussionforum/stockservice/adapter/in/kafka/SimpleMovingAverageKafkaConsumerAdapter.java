package log.qushe8r.stockdiscussionforum.stockservice.adapter.in.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.StockPriceTicker;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.SimpleMovingAverageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

//@KafkaConsumerAdapter
@Component
@RequiredArgsConstructor
public class SimpleMovingAverageKafkaConsumerAdapter {

    private final SimpleMovingAverageUseCase useCase;

    @KafkaListener(
            topics = "stock_price_ticker",
            containerFactory = "stockPriceTickerKafkaListenerContainerFactory"
    )
    public void listen(List<StockPriceTicker> stockPriceTickers) {
        System.out.println("SimpleMovingAverageKafkaConsumerAdapter: " + stockPriceTickers.size());
        useCase.operate(stockPriceTickers);
    }

}
