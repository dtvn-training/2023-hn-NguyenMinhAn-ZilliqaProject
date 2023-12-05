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
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.util.Iterator;

@Configuration
@EnableBatchProcessing
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
                .build();
    }

    

    @Bean
    public Job runJob(){
        return jobBuilderFactory.get("ZilliqaToDB")
                .start(step1())
                .next(step3())
                .next(step2())
                .build();
    }
}
