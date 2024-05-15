package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.SimpleMovingAverage;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.StockPriceTicker;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.SimpleMovingAverageUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.SimpleMovingAverageKafkaProducerPort;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.SimpleMovingAverageRedisPort;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@UseCase
@RequiredArgsConstructor
public class SimpleMovingAverageService implements SimpleMovingAverageUseCase {

    private static final Integer[] DAYS = {12, 20, 26};
    private static final String CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY = "cumulative-sum:%d:%s";
    private static final String SIMPLE_MOVING_AVERAGE_REDIS_KEY = "simple-moving-average:%d:%s";
    private static final String SIMPLE_MOVING_AVERAGE_KAFKA_TOPIC = "simple-moving-average-%d";

    private final SimpleMovingAverageRedisPort redisPort;
    private final SimpleMovingAverageKafkaProducerPort kafkaProducerPort;

    @Override
    public void operate(List<StockPriceTicker> stockPriceTickers) {
        List<String> cumulativeSumKeys = stockPriceTickers.stream()
                .flatMap(stockPriceTicker -> Arrays.stream(DAYS)
                        .map(day -> String.format(CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY, day, stockPriceTicker.itemCode())))
                .toList();

        List<BigDecimal> cumulativeSumUntilYesterday = redisPort.multiGet(cumulativeSumKeys).stream()
                .map(BigDecimal::new)
                .toList();

        Map<String, String> movingAverageRedisKeyValue = new HashMap<>();
        Map<String, List<SimpleMovingAverage>> simpleMovingAverageKafkaTopicPayload = new HashMap<>();

        List<SimpleMovingAverage> simpleMovingAverage12Day = new ArrayList<>();
        List<SimpleMovingAverage> simpleMovingAverage20Day = new ArrayList<>();
        List<SimpleMovingAverage> simpleMovingAverage26Day = new ArrayList<>();

        simpleMovingAverageKafkaTopicPayload.put(String.format(SIMPLE_MOVING_AVERAGE_KAFKA_TOPIC, DAYS[0]), simpleMovingAverage12Day);
        simpleMovingAverageKafkaTopicPayload.put(String.format(SIMPLE_MOVING_AVERAGE_KAFKA_TOPIC, DAYS[1]), simpleMovingAverage20Day);
        simpleMovingAverageKafkaTopicPayload.put(String.format(SIMPLE_MOVING_AVERAGE_KAFKA_TOPIC, DAYS[2]), simpleMovingAverage26Day);

        for (int i = 0; i < stockPriceTickers.size(); i++) {
            int length = DAYS.length;
            int idx = i * length;

            StockPriceTicker stockPriceTicker = stockPriceTickers.get(i);
            BigDecimal currentPrice = stockPriceTicker.currentPrice();
            String itemCode = stockPriceTicker.itemCode();

            BigDecimal movingAverageLineOf12Days = cumulativeSumUntilYesterday
                    .get(idx)
                    .add(currentPrice)
                    .divide(BigDecimal.valueOf(DAYS[0]), 2, RoundingMode.HALF_UP);
            BigDecimal movingAverageLineOf20Days = cumulativeSumUntilYesterday
                    .get(idx + 1)
                    .add(currentPrice)
                    .divide(BigDecimal.valueOf(DAYS[1]), 2, RoundingMode.HALF_UP);
            BigDecimal movingAverageLineOf26Days = cumulativeSumUntilYesterday
                    .get(idx + 2)
                    .add(currentPrice)
                    .divide(BigDecimal.valueOf(DAYS[2]), 2, RoundingMode.HALF_UP);

            movingAverageRedisKeyValue.put(String.format(SIMPLE_MOVING_AVERAGE_REDIS_KEY, DAYS[0], itemCode), movingAverageLineOf12Days.toPlainString());
            movingAverageRedisKeyValue.put(String.format(SIMPLE_MOVING_AVERAGE_REDIS_KEY, DAYS[1], itemCode), movingAverageLineOf20Days.toPlainString());
            movingAverageRedisKeyValue.put(String.format(SIMPLE_MOVING_AVERAGE_REDIS_KEY, DAYS[2], itemCode), movingAverageLineOf26Days.toPlainString());

            simpleMovingAverage12Day.add(new SimpleMovingAverage(itemCode, currentPrice, movingAverageLineOf12Days));
            simpleMovingAverage20Day.add(new SimpleMovingAverage(itemCode, currentPrice, movingAverageLineOf20Days));
            simpleMovingAverage26Day.add(new SimpleMovingAverage(itemCode, currentPrice, movingAverageLineOf26Days));
        }

        redisPort.multiSet(movingAverageRedisKeyValue);
        kafkaProducerPort.produce(simpleMovingAverageKafkaTopicPayload);
    }

}
