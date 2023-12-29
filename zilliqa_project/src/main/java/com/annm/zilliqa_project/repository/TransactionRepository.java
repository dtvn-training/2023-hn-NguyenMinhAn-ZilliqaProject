package com.annm.zilliqa_project.repository;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CustomRepository<Transactions, Integer> {
    List<Transactions> findByBlocksNumber(int id);

    List<Transactions> findFirst10ByOrderByAmountDesc();
    @Query("select t from Transactions t where concat(t.t_id, ' ', t.transactionId, ' ', t.blockTimestamp, ' ', t.data, ' ', t.amount, ' ', t.gasLimit, ' ',t.gasPrice, ' ', t.senderPubKey, ' ', t.sender, ' ', t.toAddress, ' ', t.version, ' ', t.cumulativeGas, ' ', t.blocks.number) like %?1%")
//    @Query("select b from Blocks b where b.mbInfoHash like %?1%")
    List<Transactions> findAllByKeyWord(String keyword);

    Transactions findById(int id);
}
