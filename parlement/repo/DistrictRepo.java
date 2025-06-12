package com.election.parlement.repo;

import com.election.parlement.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface DistrictRepo extends JpaRepository<District, Long>{
    boolean existsByName(String name);
    Optional<District> findByName(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
}
