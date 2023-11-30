package com.annm.zilliqa_project.repository;

import com.annm.zilliqa_project.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
}
