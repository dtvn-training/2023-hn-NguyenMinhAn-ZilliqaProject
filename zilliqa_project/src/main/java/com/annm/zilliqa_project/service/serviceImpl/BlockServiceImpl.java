package com.annm.zilliqa_project.service.serviceImpl;

import com.annm.zilliqa_project.entity.Blocks;
import com.annm.zilliqa_project.repository.BlockRepository;
import com.annm.zilliqa_project.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockRepository blockRepository;

    @Override
    public Long count() {
        return blockRepository.count();
    }

    @Override
    public List<Blocks> allBlocks() {
        List<Blocks> blocks = blockRepository.findAll();
        return blocks;
    }

    @Override
    public Page<Blocks> getAllBlocks(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 35);
        List<Blocks> blocksList = this.allBlocks();
        Page<Blocks> blocksPage = toPage(blocksList, pageable);
        return blocksPage;
    }

    private Page toPage(List<Blocks> list, Pageable pageable){
        if (pageable.getOffset() >= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) >= list.size())
                ? list.size() : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }
}