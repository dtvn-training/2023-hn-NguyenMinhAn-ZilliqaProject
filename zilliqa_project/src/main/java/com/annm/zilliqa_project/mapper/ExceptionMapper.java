package com.annm.zilliqa_project.mapper;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import com.google.cloud.bigquery.FieldValueList;
import org.springframework.core.convert.converter.Converter;

public class ExceptionMapper implements Converter<FieldValueList, Exceptions> {
    @Override
    public Exceptions convert(FieldValueList row) {
        String blockTimestamp = row.get("block_timestamp").getStringValue();
        int index = row.get("index").getNumericValue().intValue();
        int line = row.get("line").getNumericValue().intValue();
        String message = row.get("message").getStringValue();
        int blockNumber = row.get("block_number").getNumericValue().intValue();
        Blocks blocks = new Blocks();
        blocks.setNumber(blockNumber);
        Transactions transactions = new Transactions();
        String transactionId = row.get("transaction_id").getStringValue();
        transactions.setTransactionId(transactionId);
        Exceptions exceptions = new Exceptions();
        exceptions.setBlockTimestamp(blockTimestamp);
        exceptions.setIndex(index);
        exceptions.setLine(line);
        exceptions.setMessage(message);
        exceptions.setBlocks(blocks);
        exceptions.setTransactions(transactions);
        return exceptions;
    }
}
