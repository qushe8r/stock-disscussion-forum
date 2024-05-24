package log.qushe8r.stockdiscussionforum.stockbatch.batch.step;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockItemJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.repository.StockDailyPriceRecordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExponentialMovingAverageProcessor implements ItemProcessor<StockItemJpaEntity, Map<String, String>> {

    private static final String EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY = "exponential-moving-average:%s:%d:%s";

    private final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final StockDailyPriceRecordJpaRepository repository;

    @Override
    public Map<String, String> process(StockItemJpaEntity item) throws Exception {
        String itemCode = item.getItemCode();
        List<StockDailyPriceRecordJpaEntity> entities = repository.findAllByItemCode(itemCode);

        BigDecimal prev = BigDecimal.ZERO;

        Map<String, String> exponentialMovingAverageRedisKeyValues = new HashMap<>();
        BigDecimal ema12 = BigDecimal.ZERO;
        BigDecimal ema26 = BigDecimal.ZERO;
        String date = getRecentDate(entities);

        for (StockDailyPriceRecordJpaEntity entity : entities) {
            BigDecimal currentPrice = entity.getClosingPrice();
            if (prev.equals(BigDecimal.ZERO)) {
                prev = currentPrice;
                continue;
            }
            ema12 = getEMA(12, prev, currentPrice);
            ema26 = getEMA(26, prev, currentPrice);
        }
        exponentialMovingAverageRedisKeyValues.put(String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, date, 12, itemCode), ema12.toPlainString());
        exponentialMovingAverageRedisKeyValues.put(String.format(EXPONENTIAL_MOVING_AVERAGE_REDIS_KEY, date, 26, itemCode), ema26.toPlainString());

        return exponentialMovingAverageRedisKeyValues;
    }

    private BigDecimal getEMA(long day, BigDecimal prev, BigDecimal currentPrice) {
        BigDecimal alpha12 = BigDecimal.TWO.divide(BigDecimal.valueOf(day + 1), 2, RoundingMode.HALF_UP);
        return alpha12.multiply(currentPrice).add(BigDecimal.ONE.subtract(alpha12).multiply(prev));
    }

    private String getRecentDate(List<StockDailyPriceRecordJpaEntity> entities) {
        if (entities.isEmpty()) {
            return "00000000";
        }
        return entities.get(0).getDate().format(yyyyMMdd);
    }

}
