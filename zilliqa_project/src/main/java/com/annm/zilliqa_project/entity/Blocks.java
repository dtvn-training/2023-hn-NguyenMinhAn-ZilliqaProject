package com.annm.zilliqa_project.entity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "blocks")
public class Blocks {
    @Id
    private int number;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "difficulty_ds")
    private int difficultyDs;

    @Column(name = "gas_price")
    private int gasPrice;

    @Column(name = "ds_leader_pub_key")
    private String dsLeaderPubKey;

    @Column(name = "ds_leader_address")
    private String dsLeaderAddress;

    @Column(name = "prev_hash")
    private String prevHash;

    @Lob
    @Column(name = "signature")
    private byte[] signature;

    public Blocks(int number, Date timestamp, int difficulty, int difficultyDs, int gasPrice, String dsLeaderPubKey, String dsLeaderAddress, String prevHash, byte[] signature) {
        this.number = number;
        this.timestamp = timestamp;
        this.difficulty = difficulty;
        this.difficultyDs = difficultyDs;
        this.gasPrice = gasPrice;
        this.dsLeaderPubKey = dsLeaderPubKey;
        this.dsLeaderAddress = dsLeaderAddress;
        this.prevHash = prevHash;
        this.signature = signature;
    }

    public Blocks(Date timestamp, int difficulty, int difficultyDs, int gasPrice, String dsLeaderPubKey, String dsLeaderAddress, String prevHash, byte[] signature) {
        this.timestamp = timestamp;
        this.difficulty = difficulty;
        this.difficultyDs = difficultyDs;
        this.gasPrice = gasPrice;
        this.dsLeaderPubKey = dsLeaderPubKey;
        this.dsLeaderAddress = dsLeaderAddress;
        this.prevHash = prevHash;
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Blocks{" +
                "number=" + number +
                ", timestamp=" + timestamp +
                ", difficulty=" + difficulty +
                ", difficultyDs=" + difficultyDs +
                ", gasPrice=" + gasPrice +
                ", dsLeaderPubKey='" + dsLeaderPubKey + '\'' +
                ", dsLeaderAddress='" + dsLeaderAddress + '\'' +
                ", prevHash='" + prevHash + '\'' +
                ", signature=" + Arrays.toString(signature) +
                '}';
    }
}
