package log.qushe8r.stockdiscussionforum.stockbatch.stock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StockDailyRecord {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "stock_item_id")
    StockItem stockItem;

    LocalDate date;
    BigDecimal openingPrice;
    BigDecimal highPrice;
    BigDecimal lowPrice;
    BigDecimal closingPrice;
    BigDecimal volume;
    double foreignOwnershipPercentage;

}
