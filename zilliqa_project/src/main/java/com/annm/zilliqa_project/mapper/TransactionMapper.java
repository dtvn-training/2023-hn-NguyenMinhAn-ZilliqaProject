package com.annm.zilliqa_project.mapper;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Transactions;
import com.google.cloud.bigquery.FieldValueList;
import org.springframework.core.convert.converter.Converter;

public class TransactionMapper implements Converter<FieldValueList, Transactions> {
    @Override
    public Transactions convert(FieldValueList row) {
        String transactionId = row.get("id").getStringValue();
        Blocks blocks = new Blocks();
        int blockNumber = row.get("block_number").getNumericValue().intValue();
        blocks.setNumber(blockNumber);
        String blockTimestamp = row.get("block_timestamp").getStringValue();
        int amount = row.get("amount").getNumericValue().intValue();
        int gasLimit = row.get("gas_limit").getNumericValue().intValue();
        int gasPrice = row.get("gas_price").getNumericValue().intValue();
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
        transactions.setBlockTimestamp(blockTimestamp);
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
        return transactions;
    }
}
