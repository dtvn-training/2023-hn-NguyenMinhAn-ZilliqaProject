package com.annm.zilliqa_project.service;

import com.annm.zilliqa_project.entity.Blocks;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlockService {
    public Long count();

    List<Blocks> allBlocks();

    Page<Blocks> getAllBlocks(int pageNo);
}
