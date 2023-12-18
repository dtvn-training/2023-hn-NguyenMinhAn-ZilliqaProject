package com.annm.zilliqa_project.service.serviceImpl;

import com.annm.zilliqa_project.repository.ExceptionRepository;
import com.annm.zilliqa_project.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    @Autowired
    ExceptionRepository exceptionRepository;

    @Override
    public Long count() {
        return exceptionRepository.count();
    }
}
