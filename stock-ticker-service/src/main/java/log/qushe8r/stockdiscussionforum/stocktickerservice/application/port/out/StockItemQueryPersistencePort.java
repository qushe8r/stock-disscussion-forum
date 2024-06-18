package log.qushe8r.stockdiscussionforum.stocktickerservice.application.port.out;

import java.util.List;

import log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence.StockItemJpaEntity;

public interface StockItemQueryPersistencePort {
	List<StockItemJpaEntity> findAllStockItemsWhenLastBisinessDay();
}
