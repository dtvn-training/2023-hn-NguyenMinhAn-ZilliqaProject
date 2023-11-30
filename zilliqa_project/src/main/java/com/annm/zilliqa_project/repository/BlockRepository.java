package com.annm.zilliqa_project.repository;

import com.annm.zilliqa_project.entity.Blocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Blocks, Integer> {
}
