package log.qushe8r.stockdiscussionforum.stockbatch.batch.config;

import log.qushe8r.stockdiscussionforum.stockbatch.batch.step.StockDailyRecordWriter;
import log.qushe8r.stockdiscussionforum.stockbatch.batch.step.StockItemReader;
import log.qushe8r.stockdiscussionforum.stockbatch.batch.step.StockItemToStockDailyRecordProcessor;
import log.qushe8r.stockdiscussionforum.stockbatch.batch.task.StockItemTasklet;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockDailyPriceRecordJpaEntity;
import log.qushe8r.stockdiscussionforum.stockbatch.stock.entity.StockItemJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final StockItemTasklet stockItemTasklet;
    private final StockItemReader stockDailyRecordReader;
    private final StockItemToStockDailyRecordProcessor stockDailyRecordProcessor;
    private final StockDailyRecordWriter stockDailyRecordWriter;

    @Bean
    public Job persistHistoricalDataJob() {
        return new JobBuilder("persistHistoricalDataJob", jobRepository)
                .start(persistStockItemStep()).on("UNKNOWN").to(persistStockItemStep())
                .from(persistStockItemStep()).on("COMPLETED").to(persistStockDailyRecordStep())
                .end()
                .build();
    }


    @Bean
    public Step persistStockItemStep() {
        return new StepBuilder("persistStockItemStep", jobRepository)
                .tasklet(stockItemTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step persistStockDailyRecordStep() {
        return new StepBuilder("persistStockDailyRecordStep", jobRepository)
                .<StockItemJpaEntity, List<StockDailyPriceRecordJpaEntity>>chunk(1, transactionManager)
                .reader(stockDailyRecordReader)
                .processor(stockDailyRecordProcessor)
                .writer(stockDailyRecordWriter)
                .build();
    }

}
