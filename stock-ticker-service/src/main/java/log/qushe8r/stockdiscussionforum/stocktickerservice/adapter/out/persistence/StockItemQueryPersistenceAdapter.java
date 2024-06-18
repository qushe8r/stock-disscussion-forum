package log.qushe8r.stockdiscussionforum.stocktickerservice.adapter.out.persistence;

import java.util.List;

import log.qushe8r.stockdiscussionforum.common.PersistenceAdapter;
import log.qushe8r.stockdiscussionforum.stocktickerservice.application.port.out.StockItemQueryPersistencePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class StockItemQueryPersistenceAdapter implements StockItemQueryPersistencePort {

	private final StockItemJpaRepository stockItemJpaRepository;

	@Override
	public List<StockItemJpaEntity> findAllStockItemsWhenLastBisinessDay() {
		return stockItemJpaRepository.findAllStockItemsWhenLastBisinessDay();
	}

}
