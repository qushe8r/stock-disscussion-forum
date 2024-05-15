package log.qushe8r.stockdiscussionforum.stockservice.adapter.in.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.StockPriceTicker;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.ExponentialMovingAverageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

//@KafkaConsumerAdapter
@Component
@RequiredArgsConstructor
public class ExponentialMovingAverageKafkaConsumerAdapter {

    private final ExponentialMovingAverageUseCase useCase;

    @KafkaListener(
            topics = "stock_price_ticker",
            containerFactory = "stockPriceTickerKafkaListenerContainerFactory",
            groupId = "exponentialMovingAverageKafkaConsumerAdapter"
    )
    public void listen(List<StockPriceTicker> stockPriceTickers) {
        System.out.println("ExponentialMovingAverageKafkaConsumerAdapter: " + stockPriceTickers.size());
        useCase.operate(stockPriceTickers);
    }

}
