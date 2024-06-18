package log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence;

import java.util.List;

public interface StockItemQueryDslJpaRepository {
	List<StockItemJpaEntity> findAllStockItemsWhenLastBisinessDay();
}
