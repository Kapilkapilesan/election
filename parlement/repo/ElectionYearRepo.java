package com.election.parlement.repo;

import com.election.parlement.entity.ElectionYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionYearRepo extends JpaRepository<ElectionYear, Integer> {}
