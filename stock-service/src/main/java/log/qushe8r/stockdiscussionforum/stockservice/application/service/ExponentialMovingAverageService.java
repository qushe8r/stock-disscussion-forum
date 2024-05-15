package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.ExponentialMovingAverage;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.StockPriceTicker;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.ExponentialMovingAverageUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.ExponentialMovingAverageKafkaProducerPort;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.ExponentialMovingAverageRedisPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@UseCase
@RequiredArgsConstructor
public class ExponentialMovingAverageService implements ExponentialMovingAverageUseCase {
    private static final Integer[] DAYS = {12, 26};
    private static final String EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY = "exponential-moving-average:%s:%d:%s";
    private static final String EXPONENTIAL_MOVING_AVERAGE_KAFKA_TOPIC = "exponential-moving-average-%d";

    private final ExponentialMovingAverageRedisPort redisPort;
    private final ExponentialMovingAverageKafkaProducerPort kafkaConsumerPort;

    @Override
    public void operate(List<StockPriceTicker> stockPriceTickers) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

        List<String> yesterdayExponentialMovingAverageRedisKeys = stockPriceTickers.stream()
                .flatMap(itemCode -> Arrays.stream(DAYS)
                        .map(day -> String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, yesterday.format(yyyyMMdd), day, itemCode)))
                .toList();

        System.out.println("EXPONENTIAL:FIND:EXPONENTIAL::" + yesterdayExponentialMovingAverageRedisKeys.getFirst());

        List<String> yesterdayExponentialMovingAverageRedisValue = redisPort.multiGetExponentialMovingAverage(yesterdayExponentialMovingAverageRedisKeys);

        Map<String, String> exponentialMovingAverageRedisKeyValues = new HashMap<>();
        Map<String, List<ExponentialMovingAverage>> exponentialMovingAverageKafkaTopicPayload = new HashMap<>();

        List<ExponentialMovingAverage> todayExponentialMovingAverage12Days = new ArrayList<>();
        List<ExponentialMovingAverage> todayExponentialMovingAverage26Days = new ArrayList<>();

        for (int i = 0; i < stockPriceTickers.size(); i++) {
            int idx = i * DAYS.length;
            StockPriceTicker stockPriceTicker = stockPriceTickers.get(i);

            String itemCode = stockPriceTicker.itemCode();
            BigDecimal currentPrice = stockPriceTicker.currentPrice();
            String yesterdayExponentialMovingAverage12DayStringValue = yesterdayExponentialMovingAverageRedisValue.get(idx);
            String yesterdayExponentialMovingAverage26DayStringValue = yesterdayExponentialMovingAverageRedisValue.get(idx + 1);
            BigDecimal yesterdayExponentialMovingAverage12Day = yesterdayExponentialMovingAverage12DayStringValue == null ? BigDecimal.ZERO : new BigDecimal(yesterdayExponentialMovingAverage12DayStringValue).setScale(2, RoundingMode.HALF_UP);
            BigDecimal yesterdayExponentialMovingAverage26Day = yesterdayExponentialMovingAverage26DayStringValue == null ? BigDecimal.ZERO : new BigDecimal(yesterdayExponentialMovingAverage26DayStringValue).setScale(2, RoundingMode.HALF_UP);

            BigDecimal todayExponentialMovingAverage12Day = currentPrice.subtract(yesterdayExponentialMovingAverage12Day).multiply(getWeight(12).add(yesterdayExponentialMovingAverage12Day));
            BigDecimal todayExponentialMovingAverage26Day = currentPrice.subtract(yesterdayExponentialMovingAverage26Day).multiply(getWeight(26).add(yesterdayExponentialMovingAverage26Day));

            exponentialMovingAverageRedisKeyValues.put(String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, today.format(yyyyMMdd), 12, itemCode), todayExponentialMovingAverage12Day.toPlainString());
            exponentialMovingAverageRedisKeyValues.put(String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, today.format(yyyyMMdd), 26, itemCode), todayExponentialMovingAverage26Day.toPlainString());

            todayExponentialMovingAverage12Days.add(new ExponentialMovingAverage(itemCode, todayExponentialMovingAverage12Day));
            todayExponentialMovingAverage26Days.add(new ExponentialMovingAverage(itemCode, todayExponentialMovingAverage26Day));

        }
        exponentialMovingAverageKafkaTopicPayload.put(String.format(EXPONENTIAL_MOVING_AVERAGE_KAFKA_TOPIC, 12), todayExponentialMovingAverage12Days);
        exponentialMovingAverageKafkaTopicPayload.put(String.format(EXPONENTIAL_MOVING_AVERAGE_KAFKA_TOPIC, 26), todayExponentialMovingAverage26Days);

        redisPort.multiSetExponentialAverageRedisKeyValues(exponentialMovingAverageRedisKeyValues);
        kafkaConsumerPort.produce(exponentialMovingAverageKafkaTopicPayload);
    }

    private BigDecimal getWeight(long n) {
        return BigDecimal.TWO.divide(BigDecimal.valueOf(n + 1), 2, RoundingMode.HALF_UP);
    }

}
