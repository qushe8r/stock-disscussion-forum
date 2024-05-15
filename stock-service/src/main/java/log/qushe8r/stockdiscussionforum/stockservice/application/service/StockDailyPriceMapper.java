package log.qushe8r.stockdiscussionforum.stockservice.application.service;

import log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence.StockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockservice.application.port.in.StockDailyPriceResponse;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class StockDailyPriceMapper {
    public StockDailyPriceResponse toStockDailyPriceResponse(StockDailyPriceRecordJpaEntity stockDailyRecordJpaEntity) {
        return new StockDailyPriceResponse(
                stockDailyRecordJpaEntity.getId(),
                stockDailyRecordJpaEntity.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                stockDailyRecordJpaEntity.getOpeningPrice(),
                stockDailyRecordJpaEntity.getHighPrice(),
                stockDailyRecordJpaEntity.getLowPrice(),
                stockDailyRecordJpaEntity.getClosingPrice(),
                stockDailyRecordJpaEntity.getVolume(),
                stockDailyRecordJpaEntity.getForeignOwnershipPercentage()
        );
    }
}
