package log.qushe8r.stockdiscussionforum.stockbatch.batch.step;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockItem;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.repository.StockItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockItemReader implements ItemReader<StockItem> {

    private final StockItemRepository repository;

    private List<StockItem> list;

    @Override
    public StockItem read() {
        if (list == null) {
            list = repository.findAll();
        }
        return !this.list.isEmpty() ? this.list.removeFirst() : null;
    }

}
