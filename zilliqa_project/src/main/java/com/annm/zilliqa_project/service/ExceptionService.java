package com.annm.zilliqa_project.service;

import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;

import java.util.List;

public interface ExceptionService {
    public Long count();

    List<Exceptions> getByBlockNumber(int id);

    List<Exceptions> getByTransactionId(String id);
}
