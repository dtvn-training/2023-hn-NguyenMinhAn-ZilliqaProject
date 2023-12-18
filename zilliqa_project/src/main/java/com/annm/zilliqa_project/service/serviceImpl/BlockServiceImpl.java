package com.annm.zilliqa_project.service.serviceImpl;

import com.annm.zilliqa_project.repository.BlockRepository;
import com.annm.zilliqa_project.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockRepository blockRepository;

    @Override
    public Long count() {
        return blockRepository.count();
    }
}
