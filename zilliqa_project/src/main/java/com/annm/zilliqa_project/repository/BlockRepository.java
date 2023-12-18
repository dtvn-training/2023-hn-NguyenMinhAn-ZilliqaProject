package com.annm.zilliqa_project.repository;

import com.annm.zilliqa_project.entity.Blocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Blocks, Integer> {
    int countByNumber(int number);
}
