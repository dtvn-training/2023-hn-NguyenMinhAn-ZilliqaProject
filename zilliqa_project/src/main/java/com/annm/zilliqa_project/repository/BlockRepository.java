package com.annm.zilliqa_project.repository;

import com.annm.zilliqa_project.entity.Blocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Blocks, Integer> {
    int countByNumber(int number);

    @Query("select b from Blocks b where concat(b.number, ' ', b.dsBlockNumber, ' ', b.gasLimit, ' ', b.gasUse, ' ', b.numMicroBlocks, ' ', b.numTransactions, ' ',b.headerSignature, ' ', b.mbInfoHash, ' ', b.txLeaderAddress, ' ', b.txLeaderPubKey, ' ', b.stateDeltaHash, ' ', b.stateRootHash) like %?1%")
//    @Query("select b from Blocks b where b.mbInfoHash like %?1%")
    List<Blocks> findAllByKeyWord(String keyword);

    List<Blocks> findFirst10ByOrderByNumberDesc();
}
