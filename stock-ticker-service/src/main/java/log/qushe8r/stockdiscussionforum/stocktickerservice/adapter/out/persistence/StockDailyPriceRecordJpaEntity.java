package log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
