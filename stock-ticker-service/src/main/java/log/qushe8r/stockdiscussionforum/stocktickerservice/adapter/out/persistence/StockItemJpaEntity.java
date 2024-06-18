package log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
