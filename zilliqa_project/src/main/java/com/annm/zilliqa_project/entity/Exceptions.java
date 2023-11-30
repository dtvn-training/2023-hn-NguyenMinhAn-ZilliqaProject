package com.annm.zilliqa_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "exceptions")
public class Exceptions {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "block_number")
    private int blockNumber;
    @Column(name = "block_timestamp")
    private Date blockTimestamp;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "index")
    private int index;
    @Column(name = "line")
    private int line;
    @Column(name = "message")
    private String message;

    public Exceptions(int blockNumber, Date blockTimestamp, String transactionId, int index, int line, String message) {
        this.blockNumber = blockNumber;
        this.blockTimestamp = blockTimestamp;
        this.transactionId = transactionId;
        this.index = index;
        this.line = line;
        this.message = message;
    }

    public Exceptions(Date blockTimestamp, String transactionId, int index, int line, String message) {
        this.blockTimestamp = blockTimestamp;
        this.transactionId = transactionId;
        this.index = index;
        this.line = line;
        this.message = message;
    }

    public Exceptions(int id, int blockNumber, Date blockTimestamp, String transactionId, int index, int line, String message) {
        this.id = id;
        this.blockNumber = blockNumber;
        this.blockTimestamp = blockTimestamp;
        this.transactionId = transactionId;
        this.index = index;
        this.line = line;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Exceptions{" +
                "id=" + id +
                ", blockNumber=" + blockNumber +
                ", blockTimestamp=" + blockTimestamp +
                ", transactionId='" + transactionId + '\'' +
                ", index=" + index +
                ", line=" + line +
                ", message='" + message + '\'' +
                '}';
    }
}
