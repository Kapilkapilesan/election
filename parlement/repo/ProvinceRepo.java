package com.election.parlement.repo;


import com.election.parlement.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface ProvinceRepo extends JpaRepository<Province, Integer> {
    boolean existsByName(String name);

    @Override
    Optional<Province> findById(Integer integer);
}