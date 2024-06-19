package log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "ss_stock-items")
@NoArgsConstructor
@AllArgsConstructor
public class StockItemJpaEntity {

    @Id
    @Column(name = "item_code", unique = true, nullable = false, length = 10)
    private String itemCode;

    @Column(name = "stock_name", unique = true, nullable = false, length = 64)
    private String stockName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", nullable = false, length = 16)
    private CategoryType categoryType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stockItemJpaEntity", orphanRemoval = true)
    private List<StockDailyPriceRecordJpaEntity> stockDailyRecordJpaEntities = new ArrayList<>();

}
