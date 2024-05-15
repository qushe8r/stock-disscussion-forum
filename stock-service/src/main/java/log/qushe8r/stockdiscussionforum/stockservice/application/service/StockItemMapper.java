package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockItemJpaEntity;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockItemResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class StockItemMapper {
    public StockItemResponse toResponse(StockItemJpaEntity jpaEntity) {
        StockDailyPriceRecordJpaEntity stockDailyPriceRecordJpaEntity;
        if (jpaEntity.getStockDailyRecordJpaEntities().isEmpty()) {
            stockDailyPriceRecordJpaEntity = StockDailyPriceRecordJpaEntity.ZERO;
        } else {
            stockDailyPriceRecordJpaEntity = jpaEntity.getStockDailyRecordJpaEntities().getFirst();
        }

        return new StockItemResponse(
                jpaEntity.getItemCode(),
                jpaEntity.getStockName(),
                jpaEntity.getCategoryType().name(),
                stockDailyPriceRecordJpaEntity.getOpeningPrice(),
                stockDailyPriceRecordJpaEntity.getClosingPrice(),
                stockDailyPriceRecordJpaEntity.getVolume(),
                calFluctuationRate(
                        stockDailyPriceRecordJpaEntity.getOpeningPrice(),
                        stockDailyPriceRecordJpaEntity.getClosingPrice())
        );
    }

    private BigDecimal calFluctuationRate(BigDecimal openingPrice, BigDecimal closingPrice) {
        if (openingPrice.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        return closingPrice.subtract(openingPrice).multiply(BigDecimal.valueOf(100)).divide(openingPrice, 2, RoundingMode.HALF_UP);
    }

}
