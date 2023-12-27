package com.annm.zilliqa_project.config;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import com.annm.zilliqa_project.mapper.BlockMapper;
import com.annm.zilliqa_project.mapper.ExceptionMapper;
import com.annm.zilliqa_project.mapper.TransactionMapper;
import com.annm.zilliqa_project.repository.BlockRepository;
import com.annm.zilliqa_project.repository.ExceptionRepository;
import com.annm.zilliqa_project.repository.TransactionRepository;
import com.annm.zilliqa_project.service.BigQueryService;
import com.annm.zilliqa_project.step.BigQueryItemReader;
import com.annm.zilliqa_project.step.BigQueryItemWriter;
import com.google.cloud.bigquery.*;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@AllArgsConstructor
public class ZilliqaBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ExceptionRepository exceptionRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private JobRepository jobRepository;

    // Step for Block
    @Bean
    BigQueryItemReader<Blocks> bigQueryBlockItemReader(){
        BigQueryItemReader<Blocks> bigQueryItemReader = new BigQueryItemReader<>();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT `public-data-finance.crypto_zilliqa.tx_blocks`.*\n" +
                        "FROM (`public-data-finance.crypto_zilliqa.exceptions` INNER JOIN `public-data-finance.crypto_zilliqa.tx_blocks` \n" +
                        "      ON `public-data-finance.crypto_zilliqa.exceptions`.block_number = `public-data-finance.crypto_zilliqa.tx_blocks`.number)\n" +
                        "INNER JOIN `public-data-finance.crypto_zilliqa.transactions` \n" +
                        "      ON `public-data-finance.crypto_zilliqa.exceptions`.transaction_id = `public-data-finance.crypto_zilliqa.transactions`.id\n" +
                        "WHERE EXTRACT(YEAR FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) = 2021\n" +
                        "  AND EXTRACT(MONTH FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) = 2\n" +
                        "  AND EXTRACT(DAY FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) between 1 and 10;" +
                        "\n")
                .setUseLegacySql(false)
                .build();
        bigQueryItemReader.setJobConfiguration(queryConfig);
        BlockMapper blockMapper = new BlockMapper();
        bigQueryItemReader.setRowMapper(blockMapper);
        return bigQueryItemReader;
    }

    @Bean
    public RepositoryItemWriter<Blocks> bigQueryBlockItemWriter(){
        RepositoryItemWriter<Blocks> writer = new RepositoryItemWriter<>();
        writer.setRepository(blockRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("blocksToDB-step")
                .<Blocks, Blocks>chunk(10)
                .reader(bigQueryBlockItemReader())
                .writer(bigQueryBlockItemWriter())
                .build();
    }



    // Step for Exception
    @Bean
    BigQueryItemReader<Exceptions> bigQueryExceptionItemReader(){
        BigQueryItemReader<Exceptions> bigQueryItemReader = new BigQueryItemReader<>();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT `public-data-finance.crypto_zilliqa.exceptions`.*\n" +
                        "FROM (`public-data-finance.crypto_zilliqa.exceptions` INNER JOIN `public-data-finance.crypto_zilliqa.tx_blocks` \n" +
                        "      ON `public-data-finance.crypto_zilliqa.exceptions`.block_number = `public-data-finance.crypto_zilliqa.tx_blocks`.number)\n" +
                        "INNER JOIN `public-data-finance.crypto_zilliqa.transactions` \n" +
                        "      ON `public-data-finance.crypto_zilliqa.exceptions`.transaction_id = `public-data-finance.crypto_zilliqa.transactions`.id\n" +
                        "WHERE EXTRACT(YEAR FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) = 2021\n" +
                        "  AND EXTRACT(MONTH FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) = 2\n" +
                        "  AND EXTRACT(DAY FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) between 1 and 10;")
                .setUseLegacySql(false)
                .build();
        bigQueryItemReader.setJobConfiguration(queryConfig);
        ExceptionMapper exceptionMapper = new ExceptionMapper();
        bigQueryItemReader.setRowMapper(exceptionMapper);
        return bigQueryItemReader;
    }

    @Bean
    public RepositoryItemWriter<Exceptions> bigQueryExceptionItemWriter(){
        RepositoryItemWriter<Exceptions> writer = new RepositoryItemWriter<>();
        writer.setRepository(exceptionRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step2(){
        return stepBuilderFactory.get("exceptionsToDB-step")
                .<Exceptions, Exceptions>chunk(10)
                .reader(bigQueryExceptionItemReader())
                .writer(bigQueryExceptionItemWriter())
                .allowStartIfComplete(true)
                .build();
    }




    // Step for Exception
    @Bean
    BigQueryItemReader<Transactions> bigQueryTransactionItemReader(){
        BigQueryItemReader<Transactions> bigQueryItemReader = new BigQueryItemReader<>();
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT `public-data-finance.crypto_zilliqa.transactions`.*\n" +
                        "FROM (`public-data-finance.crypto_zilliqa.exceptions` INNER JOIN `public-data-finance.crypto_zilliqa.tx_blocks` \n" +
                        "      ON `public-data-finance.crypto_zilliqa.exceptions`.block_number = `public-data-finance.crypto_zilliqa.tx_blocks`.number)\n" +
                        "INNER JOIN `public-data-finance.crypto_zilliqa.transactions` \n" +
                        "      ON `public-data-finance.crypto_zilliqa.exceptions`.transaction_id = `public-data-finance.crypto_zilliqa.transactions`.id\n" +
                        "WHERE EXTRACT(YEAR FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) = 2021\n" +
                        "  AND EXTRACT(MONTH FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) = 2\n" +
                        "  AND EXTRACT(DAY FROM `public-data-finance.crypto_zilliqa.exceptions`.block_timestamp) between 1 and 10;")
                .setUseLegacySql(false)
                .build();
        bigQueryItemReader.setJobConfiguration(queryConfig);
        TransactionMapper transactionMapper = new TransactionMapper();
        bigQueryItemReader.setRowMapper(transactionMapper);
        return bigQueryItemReader;
    }

    @Bean
    public RepositoryItemWriter<Transactions> bigQueryTransactionItemWriter(){
        RepositoryItemWriter<Transactions> writer = new RepositoryItemWriter<>();
        writer.setRepository(transactionRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step3(){
        return stepBuilderFactory.get("transactionsToDB-step")
                .<Transactions, Transactions>chunk(10)
                .reader(bigQueryTransactionItemReader())
                .writer(bigQueryTransactionItemWriter())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job runJob1(){
        return jobBuilderFactory.get("blocksToDB")
                .start(step1())
                .build();
    }

    @Bean
    public Job runJob2(){
        return jobBuilderFactory.get("exceptionsToDB")
                .start(step2())
                .build();
    }

    @Bean
    public Job runJob3(){
        return jobBuilderFactory.get("transactionsToDB")
                .start(step3())
                .build();
    }

    @Bean
    public JobLauncher jobLauncherAsync() throws Exception
    {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

}
