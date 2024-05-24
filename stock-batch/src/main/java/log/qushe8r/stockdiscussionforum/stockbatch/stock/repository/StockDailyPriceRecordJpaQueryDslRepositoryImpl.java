package log.qushe8r.stockdiscussionforum.stockbatch.stock.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.QStockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockDailyPriceRecordJpaQueryDslRepositoryImpl implements StockDailyPriceRecordJpaQueryDslRepository {

    private static final QStockDailyPriceRecordJpaEntity Q_STOCK_DAILY_PRICE_RECORD = QStockDailyPriceRecordJpaEntity.stockDailyPriceRecordJpaEntity;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<StockDailyPriceRecordJpaEntity> find26DaysEntities(String itemCode) {
        return queryFactory.selectFrom(Q_STOCK_DAILY_PRICE_RECORD)
                .where(Q_STOCK_DAILY_PRICE_RECORD.stockItemJpaEntity.itemCode.eq(itemCode))
                .orderBy(Q_STOCK_DAILY_PRICE_RECORD.id.desc())
                .limit(26)
                .offset(0)
                .fetch();
    }

    @Override
    public List<StockDailyPriceRecordJpaEntity> findAllByItemCode(String itemCode) {
        return queryFactory.selectFrom(Q_STOCK_DAILY_PRICE_RECORD)
                .where(Q_STOCK_DAILY_PRICE_RECORD.stockItemJpaEntity.itemCode.eq(itemCode))
                .orderBy(Q_STOCK_DAILY_PRICE_RECORD.id.asc())
                .fetch();
    }

}
