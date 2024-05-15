package log.qushe8r.stockdiscussionforum.stockservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "stock-items")
@NoArgsConstructor
@AllArgsConstructor
public class StockItemJpaEntity {

    @Id
    private String itemCode;
    private String stockName;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @OneToMany(mappedBy = "stockItemJpaEntity")
    private List<StockDailyPriceRecordJpaEntity> stockDailyRecordJpaEntities = new ArrayList<>();

    public StockItemJpaEntity(String itemCode, String stockName, CategoryType categoryType) {
        this.itemCode = itemCode;
        this.stockName = stockName;
        this.categoryType = categoryType;
    }


}
