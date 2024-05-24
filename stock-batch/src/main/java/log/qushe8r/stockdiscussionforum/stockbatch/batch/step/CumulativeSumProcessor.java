package log.qushe8r.stockdiscussionforum.stockbatch.batch.step;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockItemJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.repository.StockDailyPriceRecordJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CumulativeSumProcessor implements ItemProcessor<StockItemJpaEntity, Map<String, String>> {

    private static final String CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY = "cumulative-sum:%d:%s";

    private final StockDailyPriceRecordJpaRepository stockDailyPriceRecordJpaRepository;

    @Override
    public Map<String, String> process(StockItemJpaEntity item) {
        String itemCode = item.getItemCode();
        List<StockDailyPriceRecordJpaEntity> entities
                = stockDailyPriceRecordJpaRepository.find26DaysEntities(itemCode);

        Long sumOf11Days = method(11, entities);
        Long sumOf19Days = method(19, entities);
        Long sumOf25Days = method(25, entities);

        Map<String, String> cumulativeSumRedisKeyValue = new HashMap<>();
        cumulativeSumRedisKeyValue.put(String.format(CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY, 12, itemCode), String.valueOf(sumOf11Days));
        cumulativeSumRedisKeyValue.put(String.format(CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY, 20, itemCode), String.valueOf(sumOf19Days));
        cumulativeSumRedisKeyValue.put(String.format(CUMULATIVE_SUM_UNTIL_YESTERDAY_REDIS_KEY, 26, itemCode), String.valueOf(sumOf25Days));

        return cumulativeSumRedisKeyValue;
    }

    private Long method(int day, List<StockDailyPriceRecordJpaEntity> entities) {
        return entities.stream()
                .limit(day)
                .mapToLong(entity -> entity.getClosingPrice().toBigIntegerExact().longValue())
                .sum();
    }

}
