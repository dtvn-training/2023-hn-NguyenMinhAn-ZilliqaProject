package com.annm.zilliqa_project.service.serviceImpl;

import com.annm.zilliqa_project.entity.Exceptions;
import com.annm.zilliqa_project.entity.Transactions;
import com.annm.zilliqa_project.repository.ExceptionRepository;
import com.annm.zilliqa_project.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Autowired
    ExceptionRepository exceptionRepository;

    @Override
    public Long count() {
        return exceptionRepository.count();
    }

    @Override
    public List<Exceptions> getByBlockNumber(int id) {
        List<Exceptions> exceptions =  exceptionRepository.findByBlocksNumber(id);
        return exceptions;
    }

    @Override
    public List<Exceptions> getByTransactionId(String id) {
        List<Exceptions> exceptions = exceptionRepository.findByTransactionId(id);
        return exceptions;
    }
}
