package com.annm.zilliqa_project.entity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blocks")
public class Blocks {
    @Id
    @Column(name = "number")
    private int number;

    @Column(name = "ds_block_number")
    private int dsBlockNumber;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "version")
    private int version;

    @Column(name = "gas_limit")
    private int gasLimit;

    @Column(name = "gas_use")
    private int gasUse;

    @Column(name = "mb_info_hash")
    private String mbInfoHash;

    @Column(name = "tx_leader_pub_key")
    private String txLeaderPubKey;

    @Column(name = "tx_leader_address")
    private String txLeaderAddress;

    @Column(name = "num_micro_blocks")
    private int numMicroBlocks;

    @Column(name = "num_transactions")
    private int numTransactions;

    @Column(name = "num_present_transactions")
    private int numPresentTransactions;

    @Column(name = "prev_block_hash")
    private String prevBlockHash;

    @Column(name = "rewards")
    private int rewards;

    @Column(name = "state_delta_hash")
    private String stateDeltaHash;

    @Column(name = "state_root_hash")
    private String stateRootHash;

    @Column(name = "header_signature")
    private String headerSignature;


    @OneToMany(mappedBy = "blocks", cascade = CascadeType.ALL)
    private List<Exceptions> exceptions;

    @OneToMany(mappedBy = "blocks", cascade = CascadeType.ALL)
    private List<Transactions> transactions;

    @Override
    public String toString() {
        return "Blocks{" +
                "number=" + number +
                ", dsBlockNumber=" + dsBlockNumber +
                ", timestamp='" + timestamp + '\'' +
                ", version=" + version +
                ", gasLimit=" + gasLimit +
                ", gasUse=" + gasUse +
                ", mbInfoHash='" + mbInfoHash + '\'' +
                ", txLeaderPubKey='" + txLeaderPubKey + '\'' +
                ", txLeaderAddress='" + txLeaderAddress + '\'' +
                ", numMicroBlocks=" + numMicroBlocks +
                ", numTransactions=" + numTransactions +
                ", numPresentTransactions=" + numPresentTransactions +
                ", prevBlockHash='" + prevBlockHash + '\'' +
                ", rewards=" + rewards +
                ", stateDeltaHash='" + stateDeltaHash + '\'' +
                ", stateRootHash='" + stateRootHash + '\'' +
                ", headerSignature='" + headerSignature + '\'' +
                ", exceptions=" + exceptions +
                ", transactions=" + transactions +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDsBlockNumber() {
        return dsBlockNumber;
    }

    public void setDsBlockNumber(int dsBlockNumber) {
        this.dsBlockNumber = dsBlockNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(int gasLimit) {
        this.gasLimit = gasLimit;
    }

    public int getGasUse() {
        return gasUse;
    }

    public void setGasUse(int gasUse) {
        this.gasUse = gasUse;
    }

    public String getMbInfoHash() {
        return mbInfoHash;
    }

    public void setMbInfoHash(String mbInfoHash) {
        this.mbInfoHash = mbInfoHash;
    }

    public String getTxLeaderPubKey() {
        return txLeaderPubKey;
    }

    public void setTxLeaderPubKey(String txLeaderPubKey) {
        this.txLeaderPubKey = txLeaderPubKey;
    }

    public String getTxLeaderAddress() {
        return txLeaderAddress;
    }

    public void setTxLeaderAddress(String txLeaderAddress) {
        this.txLeaderAddress = txLeaderAddress;
    }

    public int getNumMicroBlocks() {
        return numMicroBlocks;
    }

    public void setNumMicroBlocks(int numMicroBlocks) {
        this.numMicroBlocks = numMicroBlocks;
    }

    public int getNumTransactions() {
        return numTransactions;
    }

    public void setNumTransactions(int numTransactions) {
        this.numTransactions = numTransactions;
    }

    public int getNumPresentTransactions() {
        return numPresentTransactions;
    }

    public void setNumPresentTransactions(int numPresentTransactions) {
        this.numPresentTransactions = numPresentTransactions;
    }

    public String getPrevBlockHash() {
        return prevBlockHash;
    }

    public void setPrevBlockHash(String prevBlockHash) {
        this.prevBlockHash = prevBlockHash;
    }

    public int getRewards() {
        return rewards;
    }

    public void setRewards(int rewards) {
        this.rewards = rewards;
    }

    public String getStateDeltaHash() {
        return stateDeltaHash;
    }

    public void setStateDeltaHash(String stateDeltaHash) {
        this.stateDeltaHash = stateDeltaHash;
    }

    public String getStateRootHash() {
        return stateRootHash;
    }

    public void setStateRootHash(String stateRootHash) {
        this.stateRootHash = stateRootHash;
    }

    public String getHeaderSignature() {
        return headerSignature;
    }

    public void setHeaderSignature(String headerSignature) {
        this.headerSignature = headerSignature;
    }

    public List<Exceptions> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<Exceptions> exceptions) {
        this.exceptions = exceptions;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public Blocks() {
    }

    public Blocks(int dsBlockNumber, String timestamp, int version, int gasLimit, int gasUse, String mbInfoHash, String txLeaderPubKey, String txLeaderAddress, int numMicroBlocks, int numTransactions, int numPresentTransactions, String prevBlockHash, int rewards, String stateDeltaHash, String stateRootHash, String headerSignature, List<Exceptions> exceptions, List<Transactions> transactions) {
        this.dsBlockNumber = dsBlockNumber;
        this.timestamp = timestamp;
        this.version = version;
        this.gasLimit = gasLimit;
        this.gasUse = gasUse;
        this.mbInfoHash = mbInfoHash;
        this.txLeaderPubKey = txLeaderPubKey;
        this.txLeaderAddress = txLeaderAddress;
        this.numMicroBlocks = numMicroBlocks;
        this.numTransactions = numTransactions;
        this.numPresentTransactions = numPresentTransactions;
        this.prevBlockHash = prevBlockHash;
        this.rewards = rewards;
        this.stateDeltaHash = stateDeltaHash;
        this.stateRootHash = stateRootHash;
        this.headerSignature = headerSignature;
        this.exceptions = exceptions;
        this.transactions = transactions;
    }

    public Blocks(int number, int dsBlockNumber, String timestamp, int version, int gasLimit, int gasUse, String mbInfoHash, String txLeaderPubKey, String txLeaderAddress, int numMicroBlocks, int numTransactions, int numPresentTransactions, String prevBlockHash, int rewards, String stateDeltaHash, String stateRootHash, String headerSignature, List<Exceptions> exceptions, List<Transactions> transactions) {
        this.number = number;
        this.dsBlockNumber = dsBlockNumber;
        this.timestamp = timestamp;
        this.version = version;
        this.gasLimit = gasLimit;
        this.gasUse = gasUse;
        this.mbInfoHash = mbInfoHash;
        this.txLeaderPubKey = txLeaderPubKey;
        this.txLeaderAddress = txLeaderAddress;
        this.numMicroBlocks = numMicroBlocks;
        this.numTransactions = numTransactions;
        this.numPresentTransactions = numPresentTransactions;
        this.prevBlockHash = prevBlockHash;
        this.rewards = rewards;
        this.stateDeltaHash = stateDeltaHash;
        this.stateRootHash = stateRootHash;
        this.headerSignature = headerSignature;
        this.exceptions = exceptions;
        this.transactions = transactions;
    }
}
