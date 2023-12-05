package com.annm.zilliqa_project.entity;

import javax.persistence.*;

@Entity
@Table(name = "exceptions")
public class Exceptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_id")
    private int e_id;

    @Column(name = "block_timestamp")
    private String blockTimestamp;

    @Column(name = "e_index")
    private int index;
    @Column(name = "e_line")
    private int line;
    @Column(name = "e_message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "block_number")
    private Blocks blocks;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "transaction_id")
    private Transactions transactions;

    @Override
    public String toString() {
        return "Exceptions{" +
                "e_id=" + e_id +
                ", blockTimestamp='" + blockTimestamp + '\'' +
                ", index=" + index +
                ", line=" + line +
                ", message='" + message + '\'' +
                ", blocks=" + blocks +
                ", transactions=" + transactions +
                '}';
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
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

    public Exceptions(String blockTimestamp, int index, int line, String message, Blocks blocks, Transactions transactions) {
        this.blockTimestamp = blockTimestamp;
        this.index = index;
        this.line = line;
        this.message = message;
        this.blocks = blocks;
        this.transactions = transactions;
    }

    public Exceptions(int e_id, String blockTimestamp, int index, int line, String message, Blocks blocks, Transactions transactions) {
        this.e_id = e_id;
        this.blockTimestamp = blockTimestamp;
        this.index = index;
        this.line = line;
        this.message = message;
        this.blocks = blocks;
        this.transactions = transactions;
    }
}