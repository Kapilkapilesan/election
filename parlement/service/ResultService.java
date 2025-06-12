package com.election.parlement.service;

import java.util.List;

public interface ResultService {
    boolean hasElectionYear();
    int getElectionYear();
    boolean setElectionYear(int year);
    boolean saveResult(com.election.parlement.dto.ResultEntryDTO result);
    boolean savePartyVote(com.election.parlement.dto.PartyVoteDTO vote);
    List<com.election.parlement.dto.ResultEntryDTO> getResultsByDistrict(int electionYear, int districtId);
    boolean updateResult(com.election.parlement.dto.ResultEntryDTO result);
    boolean deleteResult(int electionId, int districtId);
    List<Integer> getPartiesByDistrict(int districtId);
}
