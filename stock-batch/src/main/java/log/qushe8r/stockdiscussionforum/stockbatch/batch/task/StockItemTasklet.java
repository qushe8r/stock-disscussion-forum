package log.qushe8r.stockdiscussionforum.stockbatch.batch.task;

import jakarta.annotation.Nonnull;
import log.qushe8r.stockdiscussionforum.stockbatch.client.NaverStockItemClient;
import log.qushe8r.stockdiscussionforum.stockbatch.client.dto.Page;
import log.qushe8r.stockdiscussionforum.stockbatch.client.dto.StockItemRecord;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockItemJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.CategoryType;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.repository.StockItemRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockItemTasklet implements Tasklet {

    private final NaverStockItemClient client;
    private final StockItemRepository repository;

    private Integer totalCount;
    private Integer page = 0;
    private Integer pageSize = 100;
    private CategoryType categoryType;

    @Override
    public RepeatStatus execute(@Nonnull StepContribution contribution, @NonNull ChunkContext chunkContext) {
        if (categoryType == null) {
            String jobParameterCategoryType = contribution.getStepExecution().getJobParameters().getString("categoryType");
            if (jobParameterCategoryType == null) {
                categoryType = CategoryType.CATEGORY_TYPES[0];
            }
            else {
                categoryType = CategoryType.valueOf(jobParameterCategoryType);
            }
        }

        page++;
        Page<StockItemRecord> stockItemRecordPage = client.getStockItems(categoryType.name(), page, pageSize);

        List<StockItemJpaEntity> stockItemJpaEntities = stockItemRecordPage.stocks().stream()
                .map(stockItemRecord -> new StockItemJpaEntity(stockItemRecord.itemCode(), stockItemRecord.stockName(), categoryType))
                .toList();

        repository.saveAll(stockItemJpaEntities);

        if (totalCount == null) {
            totalCount = stockItemRecordPage.totalCount();
        }

        if (totalCount <= page * pageSize) {
            if (categoryType.next() == CategoryType.CATEGORY_TYPES[0]) {
                return RepeatStatus.FINISHED;
            }
            totalCount = null;
            categoryType = categoryType.next();
            page = 0;
        }
        return RepeatStatus.CONTINUABLE;
    }

}
