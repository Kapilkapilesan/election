package com.election.parlement.repo;

import com.election.parlement.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepo extends JpaRepository<Vote, Long> {
    List<Vote> findByElectionIdAndDistrictId(int electionId, int districtId);
    void deleteByElectionIdAndDistrictId(int electionId, int districtId);
    List<Vote> findByDistrictId(int districtId);
}
