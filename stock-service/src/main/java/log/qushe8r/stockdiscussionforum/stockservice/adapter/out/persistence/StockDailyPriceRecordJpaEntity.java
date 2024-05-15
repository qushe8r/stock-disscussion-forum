package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "stock-daily-price-records")
@AllArgsConstructor
@NoArgsConstructor
public class StockDailyPriceRecordJpaEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "stock_item_id")
    StockItemJpaEntity stockItemJpaEntity;

    LocalDate date;
    BigDecimal openingPrice;
    BigDecimal highPrice;
    BigDecimal lowPrice;
    BigDecimal closingPrice;
    BigDecimal volume;
    double foreignOwnershipPercentage;

    public static final StockDailyPriceRecordJpaEntity ZERO =
            new StockDailyPriceRecordJpaEntity(
                    "",
                    null,
                    LocalDate.now(),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    0.0D
            );

}
