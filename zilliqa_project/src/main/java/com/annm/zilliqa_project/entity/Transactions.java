package com.annm.zilliqa_project.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "block_number")
    private int blockNumber;
    @Column(name = "block_timestamp")
    private Date blockTimestamp;

    @Column(name = "amount")
    private int amount;

    @Column(name = "gas_limit")
    private int gasLimit;

    @Column(name = "gas_price")
    private int gasPrice;

    @Column(name = "sender_pub_key")
    private String senderPubKey;

    @Column(name = "sender")
    private String sender;

    @Lob
    @Column(name = "signature")
    private byte[] signature;

    @Column(name = "to_addr")
    private String toAddress;

    @Column(name = "version")
    private int version;

    @Column(name = "success")
    private boolean success;

    @Column(name = "cumulative_gas")
    private int cumulativeGas;

    @Column(name = "epoch_num")
    private int epochNum;

    public Transactions(int id, String transactionId, int blockNumber, Date blockTimestamp, int amount, int gasLimit, int gasPrice, String senderPubKey, String sender, byte[] signature, String toAddress, int version, boolean success, int cumulativeGas, int epochNum) {
        this.id = id;
        this.transactionId = transactionId;
        this.blockNumber = blockNumber;
        this.blockTimestamp = blockTimestamp;
        this.amount = amount;
        this.gasLimit = gasLimit;
        this.gasPrice = gasPrice;
        this.senderPubKey = senderPubKey;
        this.sender = sender;
        this.signature = signature;
        this.toAddress = toAddress;
        this.version = version;
        this.success = success;
        this.cumulativeGas = cumulativeGas;
        this.epochNum = epochNum;
    }

    public Transactions(String transactionId, int blockNumber, Date blockTimestamp, int amount, int gasLimit, int gasPrice, String senderPubKey, String sender, byte[] signature, String toAddress, int version, boolean success, int cumulativeGas, int epochNum) {
        this.transactionId = transactionId;
        this.blockNumber = blockNumber;
        this.blockTimestamp = blockTimestamp;
        this.amount = amount;
        this.gasLimit = gasLimit;
        this.gasPrice = gasPrice;
        this.senderPubKey = senderPubKey;
        this.sender = sender;
        this.signature = signature;
        this.toAddress = toAddress;
        this.version = version;
        this.success = success;
        this.cumulativeGas = cumulativeGas;
        this.epochNum = epochNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Date getBlockTimestamp() {
        return blockTimestamp;
    }

    public void setBlockTimestamp(Date blockTimestamp) {
        this.blockTimestamp = blockTimestamp;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(int gasLimit) {
        this.gasLimit = gasLimit;
    }

    public int getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(int gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getSenderPubKey() {
        return senderPubKey;
    }

    public void setSenderPubKey(String senderPubKey) {
        this.senderPubKey = senderPubKey;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCumulativeGas() {
        return cumulativeGas;
    }

    public void setCumulativeGas(int cumulativeGas) {
        this.cumulativeGas = cumulativeGas;
    }

    public int getEpochNum() {
        return epochNum;
    }

    public void setEpochNum(int epochNum) {
        this.epochNum = epochNum;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", blockNumber=" + blockNumber +
                ", blockTimestamp=" + blockTimestamp +
                ", amount=" + amount +
                ", gasLimit=" + gasLimit +
                ", gasPrice=" + gasPrice +
                ", senderPubKey='" + senderPubKey + '\'' +
                ", sender='" + sender + '\'' +
                ", signature=" + Arrays.toString(signature) +
                ", toAddress='" + toAddress + '\'' +
                ", version=" + version +
                ", success=" + success +
                ", cumulativeGas=" + cumulativeGas +
                ", epochNum=" + epochNum +
                '}';
    }
}
