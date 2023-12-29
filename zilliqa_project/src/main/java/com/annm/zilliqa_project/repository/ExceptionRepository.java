package com.annm.zilliqa_project.repository;

import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExceptionRepository extends CustomRepository<Exceptions, Integer> {
    List<Exceptions> findByBlocksNumber(int id);

    List<Exceptions> findByTransactionId(String id);
}
