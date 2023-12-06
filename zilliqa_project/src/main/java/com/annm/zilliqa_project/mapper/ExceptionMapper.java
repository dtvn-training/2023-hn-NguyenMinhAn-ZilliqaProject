package com.annm.zilliqa_project.mapper;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import com.annm.zilliqa_project.mapper.formatter.TimestampFormatter;
import com.google.cloud.bigquery.FieldValueList;
import org.springframework.core.convert.converter.Converter;

public class ExceptionMapper implements Converter<FieldValueList, Exceptions> {
    @Override
    public Exceptions convert(FieldValueList row) {
        Double blockTimestamp = row.get("block_timestamp").getDoubleValue();
        TimestampFormatter timestampFormatter = new TimestampFormatter();
        int index = row.get("index").getNumericValue().intValue();
        int line = row.get("line").getNumericValue().intValue();
        String message = row.get("message").getStringValue();
        int blockNumber = row.get("block_number").getNumericValue().intValue();
        String transactionId = row.get("transaction_id").getStringValue();
        Blocks blocks = new Blocks();
        blocks.setNumber(blockNumber);
//        int t_id = row.get("Row").getNumericValue().intValue();
//        Transactions transactions = new Transactions();
//        transactions.setT_id(t_id);
        Exceptions exceptions = new Exceptions(transactionId);
        exceptions.setBlockTimestamp(timestampFormatter.Formatter(blockTimestamp));
        exceptions.setIndex(index);
        exceptions.setLine(line);
        exceptions.setMessage(message);
        exceptions.setBlocks(blocks);
        exceptions.setTransactionId(transactionId);
//        exceptions.setTransactions(transactions);
        return exceptions;
    }
}
