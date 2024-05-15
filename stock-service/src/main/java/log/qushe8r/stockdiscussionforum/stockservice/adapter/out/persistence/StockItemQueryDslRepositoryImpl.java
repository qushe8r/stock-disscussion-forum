package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class StockItemQueryDslRepositoryImpl implements StockItemQueryDslRepository {

    private static final QStockItemJpaEntity Q_STOCK_ITEM = QStockItemJpaEntity.stockItemJpaEntity;
    private static final QStockDailyPriceRecordJpaEntity Q_STOCK_DAILY_PRICE_RECORD = QStockDailyPriceRecordJpaEntity.stockDailyPriceRecordJpaEntity;
    private static final Set<String> Q_STOCK_ITEM_FIELD_NAME = Set.of("categoryType", "itemCode", "stockName");

    private final JPAQueryFactory queryFactory;

    @Override
    public PageImpl<StockItemJpaEntity> method(String itemName, String itemCode, String categoryType, Pageable pageable) {
        List<StockItemJpaEntity> content = queryFactory
                .selectFrom(Q_STOCK_ITEM)
                .leftJoin(Q_STOCK_ITEM.stockDailyRecordJpaEntities, Q_STOCK_DAILY_PRICE_RECORD)
                .fetchJoin()
                .where(yesterday()
                        .and(itemName(itemName))
                        .and(itemCode(itemCode))
                        .and(categoryType(categoryType))
                        .and(openingPriceNotZero()))
                .orderBy(getOrderBy(pageable))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        Long count = queryFactory
                .select(Q_STOCK_ITEM.count())
                .from(Q_STOCK_ITEM)
                .where()
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression openingPriceNotZero() {
        return Q_STOCK_DAILY_PRICE_RECORD.openingPrice.ne(BigDecimal.ZERO);
    }

    private BooleanExpression yesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1L);
        return Q_STOCK_DAILY_PRICE_RECORD.date.eq(yesterday);
    }

    private BooleanExpression itemCode(String itemCode) {
        if (itemCode == null) {
            return null;
        }
        return Q_STOCK_ITEM.itemCode.eq(itemCode);
    }

    private BooleanExpression itemName(String itemName) {
        if (itemName == null) {
            return null;
        }
        return Q_STOCK_ITEM.stockName.eq(itemName);
    }

    private BooleanExpression categoryType(String categoryType) {
        if (categoryType == null) {
            return null;
        }
        return Q_STOCK_ITEM.categoryType.eq(CategoryType.valueOf(categoryType));
    }

    private OrderSpecifier<?>[] getOrderBy(Pageable pageable) {
        Sort sort = pageable.getSort();
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        for (Sort.Order order : sort) {
            String property = order.getProperty();
            Order direction = Order.valueOf(order.getDirection().name());
            OrderSpecifier<?> orderSpecifier = getSortedColumn(direction, property);

            orderSpecifiers.add(orderSpecifier);
        }

        return orderSpecifiers.toArray(OrderSpecifier[]::new);
    }

    private OrderSpecifier<?> getSortedColumn(Order order, String fieldName) {
        if (Q_STOCK_ITEM_FIELD_NAME.contains(fieldName)) {
            return getStockItemSortedColumn(order, fieldName);
        }
        if (fieldName.equals("fluctuationRate")) {
            NumberExpression<BigDecimal> subtract = Q_STOCK_DAILY_PRICE_RECORD.closingPrice.subtract(Q_STOCK_DAILY_PRICE_RECORD.openingPrice).divide(Q_STOCK_DAILY_PRICE_RECORD.openingPrice);
            return order.equals(Order.DESC) ? subtract.desc() : subtract.asc();
        }
        return getStockDailyPriceRecordSortedColumn(order, fieldName);
    }

    private OrderSpecifier<String> getStockItemSortedColumn(Order order, String fieldName) {
        Path<String> fieldPath = Expressions.path(String.class, Q_STOCK_ITEM, fieldName);
        return new OrderSpecifier<>(order, fieldPath);
    }

    private OrderSpecifier<String> getStockDailyPriceRecordSortedColumn(Order order, String fieldName) {
        Path<String> fieldPath = Expressions.path(String.class, Q_STOCK_ITEM.stockDailyRecordJpaEntities, fieldName);
        return new OrderSpecifier<>(order, fieldPath);
    }

}
