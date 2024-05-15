package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka;

import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockPriceTickerTestKafkaProducerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

//@KafkaAdapter
@Component
@RequiredArgsConstructor
public class StockPriceTickerKafkaProducerPortAdapter implements StockPriceTickerTestKafkaProducerPort {

    private final KafkaTemplate<String, StockPriceTicker> stockPriceTickerKafkaTemplate;

    @Override
    public void produce(List<StockPriceTicker> stockPriceTickers) {
        stockPriceTickers.forEach(stockPriceTicker -> stockPriceTickerKafkaTemplate.send("stock_price_ticker", stockPriceTicker));
    }

}
