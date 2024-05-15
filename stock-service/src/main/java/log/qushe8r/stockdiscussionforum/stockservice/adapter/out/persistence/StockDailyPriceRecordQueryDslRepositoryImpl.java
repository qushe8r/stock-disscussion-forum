package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class StockDailyPriceRecordQueryDslRepositoryImpl implements StockDailyPriceRecordQueryDslRepository {

    private static final QStockDailyPriceRecordJpaEntity Q_STOCK_DAILY_PRICE_RECORD = QStockDailyPriceRecordJpaEntity.stockDailyPriceRecordJpaEntity;

    private final JPAQueryFactory queryFactory;
//
//    // 그래프를 그리기 위한 쿼리
    public List<StockDailyPriceRecordJpaEntity> query(String itemCode, Pageable pageable) {
        return queryFactory.selectFrom(Q_STOCK_DAILY_PRICE_RECORD)
                .where(Q_STOCK_DAILY_PRICE_RECORD.stockItemJpaEntity.itemCode.eq(itemCode))
                .orderBy(Q_STOCK_DAILY_PRICE_RECORD.date.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
    }

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
