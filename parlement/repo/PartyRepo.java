package com.election.parlement.repo;


import com.election.parlement.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepo extends JpaRepository<Party, Integer> {
}
