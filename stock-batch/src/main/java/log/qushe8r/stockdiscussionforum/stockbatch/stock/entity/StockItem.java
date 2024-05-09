package log.qushe8r.stockdiscussionforum.stockbatch.stock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class StockItem {

    @Id
    private String itemCode;
    private String stockName;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

}
