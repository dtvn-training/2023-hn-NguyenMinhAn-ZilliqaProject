package com.annm.zilliqa_project.service.serviceImpl;

import com.annm.zilliqa_project.repository.TransactionRepository;
import com.annm.zilliqa_project.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Long count() {
        return transactionRepository.count();
    }
}
