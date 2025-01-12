package com.tbh.backend.repository;

import com.tbh.backend.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
}
