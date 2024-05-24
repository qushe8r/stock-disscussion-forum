package log.qushe8r.stockdiscussionforum.stockbatch.batch.step;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockItemJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.repository.StockItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockItemReader implements ItemReader<StockItemJpaEntity> {

    private final StockItemRepository repository;

    private List<StockItemJpaEntity> list;

    @Override
    public StockItemJpaEntity read() {
        if (list == null) {
            list = repository.findAll();
        }
        if (this.list.isEmpty()) {
            this.list = null;
            return null;
        }
        return this.list.removeFirst();
    }

}
