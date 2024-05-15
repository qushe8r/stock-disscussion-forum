package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockDailyPriceRecordQueryDslRepository {

    List<StockDailyPriceRecordJpaEntity> query(String itemCode, Pageable pageable);

    List<StockDailyPriceRecordJpaEntity> find26DaysEntities(String itemCode);

    List<StockDailyPriceRecordJpaEntity> findAllByItemCode(String itemCode);

}
