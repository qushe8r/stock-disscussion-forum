package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.StockPriceTicker;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockTickData;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockTickDataUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockPriceTickerTestKafkaProducerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class StockTickDataService implements StockTickDataUseCase {

    private final StockPriceTickerTestKafkaProducerPort kafkaProducerPort;

    @Override
    public void produce(StockTickData stockTickData) {
        // data를 받으면 kafka로 produce
        log.info("stockTickData: {}", stockTickData);
        kafkaProducerPort.produce(new StockPriceTicker(stockTickData.itemCode(), stockTickData.currentPrice()));
    }
}
