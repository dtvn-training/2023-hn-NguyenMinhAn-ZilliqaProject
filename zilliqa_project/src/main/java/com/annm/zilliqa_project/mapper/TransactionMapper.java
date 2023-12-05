package com.annm.zilliqa_project.mapper;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Transactions;
import com.annm.zilliqa_project.mapper.formatter.TimestampFormatter;
import com.google.cloud.bigquery.FieldValueList;
import org.springframework.core.convert.converter.Converter;

public class TransactionMapper implements Converter<FieldValueList, Transactions> {
    @Override
    public Transactions convert(FieldValueList row) {
        String transactionId = row.get("id").getStringValue();
        int blockNumber = row.get("block_number").getNumericValue().intValue();
        Blocks blocks = new Blocks();
        blocks.setNumber(blockNumber);
        Double blockTimestamp = row.get("block_timestamp").getDoubleValue();
        TimestampFormatter timestampFormatter = new TimestampFormatter();
        String data = row.get("data").getStringValue();
        Long amount = row.get("amount").getNumericValue().longValue();
        int gasLimit = row.get("gas_limit").getNumericValue().intValue();
        Long gasPrice = row.get("gas_price").getNumericValue().longValue();
        String senderPubKey = row.get("sender_pub_key").getStringValue();
        String sender = row.get("sender").getStringValue();
        String signature = row.get("signature").getStringValue();
        String toAddress = row.get("to_addr").getStringValue();
        int version = row.get("version").getNumericValue().intValue();
        boolean success = row.get("success").getBooleanValue();
        int cumulativeGas = row.get("cumulative_gas").getNumericValue().intValue();
        int epochNum = row.get("epoch_num").getNumericValue().intValue();
        Transactions transactions = new Transactions();
        transactions.setTransactionId(transactionId);
        transactions.setBlocks(blocks);
        transactions.setBlockTimestamp(timestampFormatter.Formatter(blockTimestamp));
        transactions.setData(data);
        transactions.setAmount(amount);
        transactions.setGasLimit(gasLimit);
        transactions.setGasPrice(gasPrice);
        transactions.setSenderPubKey(senderPubKey);
        transactions.setSender(sender);
        transactions.setSignature(signature);
        transactions.setToAddress(toAddress);
        transactions.setVersion(version);
        transactions.setSuccess(success);
        transactions.setCumulativeGas(cumulativeGas);
        transactions.setEpochNum(epochNum);
        System.out.println(transactions.toString());
        return transactions;
    }
}
