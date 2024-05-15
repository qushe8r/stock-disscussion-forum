package log.qushe8r.stockdiscussionforum.stockservice.application.port.out;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.StockPriceTicker;

import java.util.List;

public interface StockPriceTickerTestKafkaProducerPort {

    void produce(List<StockPriceTicker> list);

}
