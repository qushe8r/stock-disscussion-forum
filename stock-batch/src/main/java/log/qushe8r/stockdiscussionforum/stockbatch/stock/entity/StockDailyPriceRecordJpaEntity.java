package log.qushe8r.stockdiscussionforum.stockbatch.stock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "ss_stock-daily-price-records")
@AllArgsConstructor
@NoArgsConstructor
public class StockDailyPriceRecordJpaEntity {

    @Id
    @Column(name = "stock_daily_price_record_id", unique = true, nullable = false, length = 20)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_item_id", nullable = false, updatable = false)
    private StockItemJpaEntity stockItemJpaEntity;

    @Column(name = "date", nullable = false, columnDefinition = "date")
    private LocalDate date;

    @Column(name = "opening_price", nullable = false, precision = 15, scale = 3)
    private BigDecimal openingPrice;

    @Column(name = "high_price", nullable = false, precision = 15, scale = 3)
    private BigDecimal highPrice;

    @Column(name = "low_price", nullable = false, precision = 15, scale = 3)
    private BigDecimal lowPrice;

    @Column(name = "closing_price", nullable = false, precision = 15, scale = 3)
    private BigDecimal closingPrice;

    @Column(name = "volume", nullable = false, precision = 20, scale = 3)
    private BigDecimal volume;

    @Column(name = "foreign_ownership_percentage", nullable = false)
    private double foreignOwnershipPercentage;

}
