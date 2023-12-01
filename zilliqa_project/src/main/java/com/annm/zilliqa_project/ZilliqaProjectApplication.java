package com.annm.zilliqa_project;

import com.annm.zilliqa_project.service.BigQueryService;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import net.minidev.json.JSONUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Iterator;

@SpringBootApplication
public class ZilliqaProjectApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ZilliqaProjectApplication.class, args);
		BigQueryService bigQueryService;
		BigQuery bigQuery = BigQueryOptions.newBuilder().setProjectId("blissful-hash-405809").setCredentials(BigQueryService.getCredentials()).build().getService();
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT * FROM `blissful-hash-405809.data_demo.customer` WHERE id < 500;")
				.setUseLegacySql(false)
				.build();
		Iterable<FieldValueList> result = bigQuery.query(queryConfig).getValues();
		System.out.println(result);
	}


}

