package log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockItemQueryDslJpaRepositoryImpl implements StockItemQueryDslJpaRepository {

    private final static QStockDailyPriceRecordJpaEntity DAILY_PRICE_RECORD = new QStockDailyPriceRecordJpaEntity("dr");
    private final static QStockItemJpaEntity STOCK_ITEM = new QStockItemJpaEntity("si");

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StockItemJpaEntity> findAllStockItemsWhenLastBisinessDay() {
        LocalDate lastBusinessDay = LocalDate.now().minusDays(2);
        int value = lastBusinessDay.getDayOfWeek().getValue();
        if (lastBusinessDay.getDayOfWeek().getValue() >= 6) {
            lastBusinessDay = lastBusinessDay.minusDays(value - 5);
        }

        return queryFactory.selectFrom(STOCK_ITEM)
                .join(STOCK_ITEM.stockDailyRecordJpaEntities, DAILY_PRICE_RECORD)
                .fetchJoin()
                .where(DAILY_PRICE_RECORD.date.eq(lastBusinessDay))
                .orderBy(STOCK_ITEM.itemCode.asc())
                .fetch();
    }

}
