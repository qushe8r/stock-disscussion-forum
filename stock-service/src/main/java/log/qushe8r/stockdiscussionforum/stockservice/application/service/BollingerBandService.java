package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.BollingerBand;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.SimpleMovingAverage;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.BollingerBandUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.BollingerBandKafkaProducerPort;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.BollingerBandRedisPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class BollingerBandService implements BollingerBandUseCase {

    private static final String SIMPLE_MOVING_AVERAGE_REDIS_KEY = "simple-moving-average:%d:%s";
    private static final String BOLLINGER_BAND_REDIS_KEY = "bollinger-band:%s:%s";
    private static final String BOLLINGER_BAND_KAFKA_TOPIC = "bollinger-band";
    private static final String HIGH = "high";
    private static final String LOW = "low";

    private final BollingerBandRedisPort redisPort;
    private final BollingerBandKafkaProducerPort kafkaProducerPort;

    @Override
    public void operate(List<SimpleMovingAverage> simpleMovingAverages) {
        List<String> movingAverageRedisKeys = simpleMovingAverages.stream()
                .map(SimpleMovingAverage::itemCode)
                .map(itemCode -> String.format(SIMPLE_MOVING_AVERAGE_REDIS_KEY, 20, itemCode))
                .toList();

        List<String> movingAverageRedisValues = redisPort.multiGetMovingAverage(movingAverageRedisKeys);

        Map<String, String> bollingerBandRedisKeyValues = new HashMap<>();
        Map<String, List<BollingerBand>> bollingerBandKafkaTopicPayloads = new HashMap<>();
        List<BollingerBand> bollingerBands = new ArrayList<>();
        bollingerBandKafkaTopicPayloads.put(BOLLINGER_BAND_KAFKA_TOPIC, bollingerBands);

        for (int i = 0; i < simpleMovingAverages.size(); i++) {
            SimpleMovingAverage simpleMovingAverage = simpleMovingAverages.get(i);
            String itemCode = simpleMovingAverage.itemCode();
            BigDecimal currentPrice = simpleMovingAverage.currentPrice();

            String movingAverageStringValue = movingAverageRedisValues.get(i);
            BigDecimal movingAverage = movingAverageStringValue == null ? BigDecimal.ZERO : new BigDecimal(movingAverageStringValue);

            BigDecimal highBollingerBand = movingAverage.add(currentPrice.multiply(BigDecimal.TWO));
            BigDecimal lowBollingerBand = movingAverage.subtract(currentPrice.multiply(BigDecimal.TWO));

            bollingerBandRedisKeyValues.put(String.format(BOLLINGER_BAND_REDIS_KEY, itemCode, HIGH), highBollingerBand.toPlainString());
            bollingerBandRedisKeyValues.put(String.format(BOLLINGER_BAND_REDIS_KEY, itemCode, LOW), lowBollingerBand.toPlainString());

            bollingerBands.add(new BollingerBand(itemCode, highBollingerBand, lowBollingerBand));
        }

        redisPort.multiSet(bollingerBandRedisKeyValues);
        kafkaProducerPort.produce(bollingerBandKafkaTopicPayloads);
    }

}
