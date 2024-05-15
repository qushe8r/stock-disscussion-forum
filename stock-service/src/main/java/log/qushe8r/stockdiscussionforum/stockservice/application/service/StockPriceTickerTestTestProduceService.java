package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.common.UseCase;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.kafka.StockPriceTicker;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockDailyPriceRecordJpaRepository;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockItemJpaEntity;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockItemJpaRepository;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockPriceTickerTestProduceUseCase;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockPriceTickerTestKafkaProducerPort;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.out.StockPriceTickerTestQueryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UseCase
@RequiredArgsConstructor
public class StockPriceTickerTestTestProduceService implements StockPriceTickerTestProduceUseCase {

    private static final String CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY = "cumulative-sum:%d:%s";
    private static final String EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY = "exponential-moving-average:%s:%d:%s";

    private final StockPriceTickerTestQueryPersistencePort persistencePort;
    private final StockPriceTickerTestKafkaProducerPort kafkaProducerPort;
    private final RedisTemplate<String, String> redisTemplate;
    private final StockItemJpaRepository stockItemJpaRepository;
    private final StockDailyPriceRecordJpaRepository stockDailyPriceRecordJpaRepository;

    @Override
    public void produce() {
        List<StockPriceTicker> list = persistencePort.findAllQuery().stream()
                .map(stockDailyPriceRecordJpaEntity -> new StockPriceTicker(
                        stockDailyPriceRecordJpaEntity.getStockItemJpaEntity().getItemCode(),
                        stockDailyPriceRecordJpaEntity.getClosingPrice().multiply(BigDecimal.valueOf(Math.random() * 0.1))))
                .toList();
        kafkaProducerPort.produce(list);
    }

    @Override
    public void setSimpleMovingAverageToRedis() {
        List<StockItemJpaEntity> all = stockItemJpaRepository.findAll();
        all.forEach(stockItem -> {
            // StockItem 26개 가져와서
            String itemCode = stockItem.getItemCode();
            List<StockDailyPriceRecordJpaEntity> entities = stockDailyPriceRecordJpaRepository.find26DaysEntities(itemCode);
            // 평균 계산하기 [ 12, 20 ,26 ]
            long sumOf12Days = entities.stream()
                    .limit(11)
                    .mapToLong(entity -> entity.getClosingPrice().toBigInteger().longValue())
                    .sum();
            long sumOf20Days = entities.stream()
                    .limit(19)
                    .mapToLong(entity -> entity.getClosingPrice().toBigInteger().longValue())
                    .sum();
            long sumOf26Days = entities.stream()
                    .limit(25)
                    .mapToLong(entity -> entity.getClosingPrice().toBigInteger().longValue())
                    .sum();
            // redis에 입력 key: CUMULATIVE_SUM:%d:%s
            Map<String, String> cumulativeSumRedisKeyValue = new HashMap<>();
            cumulativeSumRedisKeyValue.put(String.format(CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY, 12, itemCode), String.valueOf(sumOf12Days));
            cumulativeSumRedisKeyValue.put(String.format(CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY, 20, itemCode), String.valueOf(sumOf20Days));
            cumulativeSumRedisKeyValue.put(String.format(CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY, 26, itemCode), String.valueOf(sumOf26Days));

            redisTemplate.opsForValue().multiSet(cumulativeSumRedisKeyValue);
        });
    }

    @Override
    public void setExponentialMovingAverageToRedis() {
        List<StockItemJpaEntity> all = stockItemJpaRepository.findAll();
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        all.forEach(stockItem -> {
            String itemCode = stockItem.getItemCode();
            List<StockDailyPriceRecordJpaEntity> allByItemCode = stockDailyPriceRecordJpaRepository.findAllByItemCode(itemCode);

            BigDecimal prev = BigDecimal.ZERO;

            Map<String, String> exponentialMovingAverageRedisKeyValue = new HashMap<>();
            BigDecimal ema12 = null;
            BigDecimal ema26 = null;
            LocalDate date = LocalDate.now().minusDays(1);
            for (StockDailyPriceRecordJpaEntity stockDailyPriceRecordJpaEntity : allByItemCode) {
                BigDecimal currentPrice = stockDailyPriceRecordJpaEntity.getClosingPrice();
                if (prev.equals(BigDecimal.ZERO)) {
                    prev = currentPrice;
                    continue;
                }

                BigDecimal alpha12 = BigDecimal.TWO.divide(BigDecimal.valueOf(13), 2, RoundingMode.HALF_UP);
                BigDecimal alpha26 = BigDecimal.ONE.divide(BigDecimal.valueOf(27), 2, RoundingMode.HALF_UP);

                ema12 = alpha12.multiply(currentPrice).add(BigDecimal.ONE.subtract(alpha12).multiply(prev));
                ema26 = alpha12.multiply(currentPrice).add(BigDecimal.ONE.subtract(alpha26).multiply(prev));
            }

            System.out.printf("TEST_START:EXPONENTIAL::" + (EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY) + "\n", date.format(yyyyMMdd), 12, itemCode);
            if (ema12 != null || ema26 != null) {
                System.out.println("not null:" + String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, date.format(yyyyMMdd), 12, itemCode));
                exponentialMovingAverageRedisKeyValue.put(String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, date.format(yyyyMMdd), 12, itemCode), ema12.toPlainString());
                exponentialMovingAverageRedisKeyValue.put(String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, date.format(yyyyMMdd), 26, itemCode), ema26.toPlainString());
            } else {
                System.out.println("null:" + String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, date.format(yyyyMMdd), 12, itemCode));
                exponentialMovingAverageRedisKeyValue.put(String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, date.format(yyyyMMdd), 12, itemCode), prev.toPlainString());
                exponentialMovingAverageRedisKeyValue.put(String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, date.format(yyyyMMdd), 26, itemCode), prev.toPlainString());
            }
            redisTemplate.opsForValue().multiSet(exponentialMovingAverageRedisKeyValue);
        });
    }

}
