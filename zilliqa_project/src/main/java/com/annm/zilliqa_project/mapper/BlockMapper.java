package com.annm.zilliqa_project.mapper;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.mapper.formatter.TimestampFormatter;
import com.google.api.client.util.DateTime;
import com.google.cloud.bigquery.FieldValueList;
import org.springframework.core.convert.converter.Converter;
import org.threeten.bp.DateTimeUtils;

import java.util.Date;

public class BlockMapper implements Converter<FieldValueList, Blocks> {
    @Override
    public Blocks convert(FieldValueList row) {
        int number = row.get("number").getNumericValue().intValue();
        int dsBlockNumber = row.get("ds_block_number").getNumericValue().intValue();
        Double blockTimestamp = row.get("timestamp").getDoubleValue();
        TimestampFormatter timestampFormatter = new TimestampFormatter();
        int version = row.get("version").getNumericValue().intValue();
        int gasLimit = row.get("gas_limit").getNumericValue().intValue();
        int gasUse = row.get("gas_used").getNumericValue().intValue();
        String mbInfoHash = row.get("mb_info_hash").getStringValue();
        String txLeaderAddress = row.get("tx_leader_address").getStringValue();
        String txLeaderPubKey = row.get("tx_leader_pub_key").getStringValue();
        int numMicroBlocks = row.get("num_micro_blocks").getNumericValue().intValue();
        int numTransactions = row.get("num_transactions").getNumericValue().intValue();
        int numPresentTransactions = row.get("num_present_transactions").getNumericValue().intValue();
        int rewards = row.get("rewards").getNumericValue().intValue();
        String prevBlockHash = row.get("prev_block_hash").getStringValue();
        String stateDeltaHash = row.get("state_delta_hash").getStringValue();
        String stateRootHash = row.get("state_root_hash").getStringValue();
        String headerSignature = row.get("header_signature").getStringValue();
        Blocks block = new Blocks();
        block.setNumber(number);
        block.setDsBlockNumber(dsBlockNumber);
        block.setTimestamp(timestampFormatter.Formatter(blockTimestamp));
        block.setVersion(version);
        block.setGasLimit(gasLimit);
        block.setGasUse(gasUse);
        block.setMbInfoHash(mbInfoHash);
        block.setTxLeaderAddress(txLeaderAddress);
        block.setTxLeaderPubKey(txLeaderPubKey);
        block.setNumMicroBlocks(numMicroBlocks);
        block.setNumTransactions(numTransactions);
        block.setNumPresentTransactions(numPresentTransactions);
        block.setRewards(rewards);
        block.setPrevBlockHash(prevBlockHash);
        block.setStateDeltaHash(stateDeltaHash);
        block.setStateRootHash(stateRootHash);
        block.setHeaderSignature(headerSignature);
        return block;
    }


}
