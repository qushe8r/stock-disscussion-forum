package log.qushe8r.stockdiscussionforum.stockbatch.batch.step;

import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.repository.StockDailyPriceRecordJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockDailyRecordWriter implements ItemWriter<List<StockDailyPriceRecordJpaEntity>> {

    private final StockDailyPriceRecordJpaRepository repository;

    @Override
    public void write(Chunk<? extends List<StockDailyPriceRecordJpaEntity>> chunk) {
        chunk.getItems().forEach(repository::bulkInsert);
    }

}
