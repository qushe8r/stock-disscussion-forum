package log.qushe8r.stockdiscussionforum.stockbatch.stock.repository;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockDailyPriceRecordBulkInsertJdbcTemplateImpl implements StockDailyPriceRecordBulkInsertJdbcTemplate {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void bulkInsert(List<StockDailyPriceRecordJpaEntity> stockDailyPriceRecordJpaEntities) {
        String sql = """
                INSERT INTO `stock-daily-price-records` (id, stock_item_id, date, opening_price, high_price, low_price, closing_price, volume, foreign_ownership_percentage)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                StockDailyPriceRecordJpaEntity stockDailyPriceRecordJpaEntity = stockDailyPriceRecordJpaEntities.get(i);
                ps.setString(1, stockDailyPriceRecordJpaEntity.getId());
                ps.setString(2, stockDailyPriceRecordJpaEntity.getStockItemJpaEntity().getItemCode());
                ps.setDate(3, Date.valueOf(stockDailyPriceRecordJpaEntity.getDate()));
                ps.setBigDecimal(4, stockDailyPriceRecordJpaEntity.getOpeningPrice());
                ps.setBigDecimal(5, stockDailyPriceRecordJpaEntity.getHighPrice());
                ps.setBigDecimal(6, stockDailyPriceRecordJpaEntity.getLowPrice());
                ps.setBigDecimal(7, stockDailyPriceRecordJpaEntity.getClosingPrice());
                ps.setBigDecimal(8, stockDailyPriceRecordJpaEntity.getVolume());
                ps.setDouble(9, stockDailyPriceRecordJpaEntity.getForeignOwnershipPercentage());
            }

            @Override
            public int getBatchSize() {
                return stockDailyPriceRecordJpaEntities.size();
            }
        });
    }
}
