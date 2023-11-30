package com.annm.zilliqa_project.repository;

import com.annm.zilliqa_project.entity.Exceptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExceptionRepository extends JpaRepository<Exceptions, Integer> {
}
