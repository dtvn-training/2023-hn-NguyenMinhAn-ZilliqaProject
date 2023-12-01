package com.annm.zilliqa_project.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exceptions")
public class Exceptions {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "block_timestamp")
    private String blockTimestamp;

    @Column(name = "index")
    private int index;
    @Column(name = "line")
    private int line;
    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "block_number", referencedColumnName = "number")
    private Blocks blocks;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    private Transactions transactions;

    @Override
    public String toString() {
        return "Exceptions{" +
                "id=" + id +
                ", blockTimestamp='" + blockTimestamp + '\'' +
                ", index=" + index +
                ", line=" + line +
                ", message='" + message + '\'' +
                ", blocks=" + blocks +
                ", transactions=" + transactions +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockTimestamp() {
        return blockTimestamp;
    }

    public void setBlockTimestamp(String blockTimestamp) {
        this.blockTimestamp = blockTimestamp;
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

    public Blocks getBlocks() {
        return blocks;
    }

    public void setBlocks(Blocks blocks) {
        this.blocks = blocks;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public Exceptions() {
    }

    public Exceptions(String blockTimestamp, String transactionId, int index, int line, String message, Blocks blocks, Transactions transactions) {
        this.blockTimestamp = blockTimestamp;

        this.index = index;
        this.line = line;
        this.message = message;
        this.blocks = blocks;
        this.transactions = transactions;
    }

    public Exceptions(int id, String blockTimestamp, String transactionId, int index, int line, String message, Blocks blocks, Transactions transactions) {
        this.id = id;
        this.blockTimestamp = blockTimestamp;

        this.index = index;
        this.line = line;
        this.message = message;
        this.blocks = blocks;
        this.transactions = transactions;
    }
}
