package com.annm.zilliqa_project.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @Column(name = "transaction_id")
    private int transactionId;

    @ManyToOne
    @JoinColumn
    private Blocks blocks;

    @Column(name = "block_timestamp")
    private String blockTimestamp;

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

    @OneToMany(mappedBy = "transactions")
    private List<Exceptions> exceptions;

    @Override
    public String toString() {
        return "Transactions{" +
                "transactionId=" + transactionId +
                ", blocks=" + blocks +
                ", blockTimestamp='" + blockTimestamp + '\'' +
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
                ", exceptions=" + exceptions +
                '}';
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Blocks getBlocks() {
        return blocks;
    }

    public void setBlocks(Blocks blocks) {
        this.blocks = blocks;
    }

    public String getBlockTimestamp() {
        return blockTimestamp;
    }

    public void setBlockTimestamp(String blockTimestamp) {
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

    public List<Exceptions> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<Exceptions> exceptions) {
        this.exceptions = exceptions;
    }

    public Transactions() {
    }

    public Transactions(Blocks blocks, String blockTimestamp, int amount, int gasLimit, int gasPrice, String senderPubKey, String sender, String signature, String toAddress, int version, boolean success, int cumulativeGas, int epochNum, List<Exceptions> exceptions) {
        this.blocks = blocks;
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
        this.exceptions = exceptions;
    }

    public Transactions(int transactionId, Blocks blocks, String blockTimestamp, int amount, int gasLimit, int gasPrice, String senderPubKey, String sender, String signature, String toAddress, int version, boolean success, int cumulativeGas, int epochNum, List<Exceptions> exceptions) {
        this.transactionId = transactionId;
        this.blocks = blocks;
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
        this.exceptions = exceptions;
    }
}
