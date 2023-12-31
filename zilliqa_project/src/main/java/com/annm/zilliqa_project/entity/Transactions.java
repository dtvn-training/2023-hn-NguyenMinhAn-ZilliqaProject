package com.annm.zilliqa_project.entity;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id")
    private int t_id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "block_timestamp")
    private String blockTimestamp;

    @Column(name = "data", columnDefinition = "longtext")
    private String data;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "gas_limit")
    private int gasLimit;

    @Column(name = "gas_price")
    private Long gasPrice;

    @Column(name = "sender_pub_key")
    private String senderPubKey;

    @Column(name = "sender")
    private String sender;

    @Column(name = "signature")
    private String signature;

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

    @ManyToOne
    @JoinColumn(name = "block_number")
    private Blocks blocks;

    public Transactions(int t_id, String transactionId, String blockTimestamp, String data, Long amount, int gasLimit, Long gasPrice, String senderPubKey, String sender, String signature, String toAddress, int version, boolean success, int cumulativeGas, int epochNum, List<Exceptions> exceptions, Blocks blocks) {
        this.t_id = t_id;
        this.transactionId = transactionId;
        this.blockTimestamp = blockTimestamp;
        this.data = data;
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
        this.blocks = blocks;
    }

    public Transactions(String transactionId, String blockTimestamp, String data, Long amount, int gasLimit, Long gasPrice, String senderPubKey, String sender, String signature, String toAddress, int version, boolean success, int cumulativeGas, int epochNum, List<Exceptions> exceptions, Blocks blocks) {
        this.transactionId = transactionId;
        this.blockTimestamp = blockTimestamp;
        this.data = data;
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
        this.blocks = blocks;
    }

    public Transactions() {
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "t_id=" + t_id +
                ", transactionId='" + transactionId + '\'' +
                ", blockTimestamp='" + blockTimestamp + '\'' +
                ", data='" + data + '\'' +
                ", amount=" + amount +
                ", gasLimit=" + gasLimit +
                ", gasPrice=" + gasPrice +
                ", senderPubKey='" + senderPubKey + '\'' +
                ", sender='" + sender + '\'' +
                ", signature='" + signature + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", version=" + version +
                ", success=" + success +
                ", cumulativeGas=" + cumulativeGas +
                ", epochNum=" + epochNum +
                ", blocks=" + blocks +
                '}';
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBlockTimestamp() {
        return blockTimestamp;
    }

    public void setBlockTimestamp(String blockTimestamp) {
        this.blockTimestamp = blockTimestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public int getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(int gasLimit) {
        this.gasLimit = gasLimit;
    }

    public Long getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(Long gasPrice) {
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
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

    public Blocks getBlocks() {
        return blocks;
    }

    public void setBlocks(Blocks blocks) {
        this.blocks = blocks;
    }
}
