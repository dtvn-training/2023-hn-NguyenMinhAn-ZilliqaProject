package com.annm.zilliqa_project.service;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.entity.Transactions;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    public Long count();

    List<Transactions> getByBlockNumber(int id);

    List<Transactions> findTop10Transactions();

    Page<Transactions> searchTransactions(int pageNo, String keyword);

    public Transactions getByNumber(int id);

    Transactions getById(int id);
}
