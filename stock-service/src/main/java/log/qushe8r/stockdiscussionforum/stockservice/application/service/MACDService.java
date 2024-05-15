package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.MACD;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.SimpleMovingAverage;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.MACDUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.MACDKafkaProducerPort;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.MACDRedisPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@UseCase
@RequiredArgsConstructor
public class MACDService implements MACDUseCase {
    private static final Integer[] DAYS = {12, 26};
    private static final String EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY = "exponential-moving-average:%s:%d:%s";
    private static final String MACD_REDIS_KEY = "macd:%s";
    private static final String MACD_KAFKA_TOPIC = "macd";

    private final MACDRedisPort redisPort;
    private final MACDKafkaProducerPort kafkaProducerPort;

    @Override
    public void operate(List<SimpleMovingAverage> simpleMovingAverages) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");


        List<String> exponentialMovingAverageRedisKeys = simpleMovingAverages.stream()
                .map(SimpleMovingAverage::itemCode)
                .flatMap(itemCode -> Arrays.stream(DAYS)
                        .map(numberOfDays -> String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, today.format(yyyyMMdd), numberOfDays, itemCode)))
                .toList();

        System.out.println("MACDService::EXPONENTIAL::" + exponentialMovingAverageRedisKeys.getFirst());

        List<String> exponentialMovingAverageRedisValues = redisPort.multiGetExponentialMovingAverages(exponentialMovingAverageRedisKeys);

        Map<String, String> mACDRedisKeyValues = new HashMap<>();
        Map<String, List<MACD>> mACDKafkaTopicPayloads = new HashMap<>();
        List<MACD> mACDKafkaPayloads = new ArrayList<>();
        mACDKafkaTopicPayloads.put(MACD_KAFKA_TOPIC, mACDKafkaPayloads);
        for (int i = 0; i < simpleMovingAverages.size(); i++) {
            int idx = i * DAYS.length;
            SimpleMovingAverage simpleMovingAverage = simpleMovingAverages.get(i);
            String itemCode = simpleMovingAverage.itemCode();
            String exponentialMovingAverage12DayStringValue = exponentialMovingAverageRedisValues.get(idx);
            String exponentialMovingAverage26DayStringValue = exponentialMovingAverageRedisValues.get(idx + 1);
            BigDecimal exponentialMovingAverage12Day = exponentialMovingAverage12DayStringValue == null ? BigDecimal.ZERO : new BigDecimal(exponentialMovingAverage12DayStringValue);
            BigDecimal exponentialMovingAverage26Day = exponentialMovingAverage26DayStringValue == null ? BigDecimal.ZERO : new BigDecimal(exponentialMovingAverage26DayStringValue);
            BigDecimal mACD = exponentialMovingAverage12Day.add(exponentialMovingAverage26Day).divide(BigDecimal.TWO, 2, RoundingMode.HALF_UP);

            mACDRedisKeyValues.put(String.format(MACD_REDIS_KEY, itemCode), mACD.toPlainString());
            mACDKafkaPayloads.add(new MACD(itemCode, mACD));
        }

        redisPort.multiSetMACD(mACDRedisKeyValues);
        kafkaProducerPort.produce(mACDKafkaTopicPayloads);
    }

}
