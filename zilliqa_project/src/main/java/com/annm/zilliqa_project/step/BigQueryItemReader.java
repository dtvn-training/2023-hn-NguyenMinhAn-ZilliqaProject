package com.annm.zilliqa_project.step;

import com.annm.zilliqa_project.service.BigQueryService;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.Objects;

public class BigQueryItemReader<T> implements ItemReader<T>, InitializingBean {
    private Iterator<FieldValueList> iterator;

    private Converter<FieldValueList, T> rowMapper;

    private QueryJobConfiguration queryConfig;

    public void setJobConfiguration(QueryJobConfiguration queryConfig) {
        this.queryConfig = queryConfig;
    }

    public void setRowMapper(Converter<FieldValueList, T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    BigQueryService bigQueryService;
    BigQuery bigQuery = BigQueryOptions.newBuilder().setProjectId("arched-alpha-407106").setCredentials(BigQueryService.getCredentials()).build().getService();

    @Override
    public T read() throws Exception{
        if (Objects.isNull(iterator)){
            doOpen();
        }
        return iterator.hasNext() ? rowMapper.convert(iterator.next()) : null;
    }
    private void doOpen() throws Exception {
        iterator = bigQuery.query(queryConfig).getValues().iterator();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.bigQuery, "BigQuery service must be provided");
        Assert.notNull(this.rowMapper, "Row mapper must be provided");
        Assert.notNull(this.queryConfig, "Job configuration must be provided");
    }
}
