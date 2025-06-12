package com.election.parlement.service;

import com.election.parlement.dto.*;
import com.election.parlement.entity.*;
import com.election.parlement.repo.ElectionYearRepo;
import com.election.parlement.repo.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private VoteRepo voteRepo;

    @Autowired
    private ElectionYearRepo yearRepo;

    @Override
    public boolean hasElectionYear() {
        return yearRepo.findById(1).map(y -> y.getYear() > 0).orElse(false);
    }

    @Override
    public int getElectionYear() {
        return yearRepo.findById(1).map(ElectionYear::getYear).orElse(-1);
    }

    @Override
    public boolean setElectionYear(int year) {
        ElectionYear ey = new ElectionYear();
        ey.setId(1);
        ey.setYear(year);
        yearRepo.save(ey);
        return true;
    }

    @Override
    public boolean saveResult(com.election.parlement.dto.ResultEntryDTO result) {
        for (com.election.parlement.dto.PartyVoteDTO pv : result.partyVotes) {
            Vote vote = new Vote();
            vote.setElectionId(result.electionId);
            vote.setDistrictId(result.districtId);
            vote.setPartyId(pv.partyId);
            vote.setVotes(pv.votes);
            voteRepo.save(vote);
        }
        return true;
    }

    @Override
    public boolean savePartyVote(com.election.parlement.dto.PartyVoteDTO pv) {
        Vote vote = new Vote();
        vote.setElectionId(pv.electionId);
        vote.setDistrictId(pv.districtId);
        vote.setPartyId(pv.partyId);
        vote.setVotes(pv.votes);
        voteRepo.save(vote);
        return true;
    }

    @Override
    public List<com.election.parlement.dto.ResultEntryDTO> getResultsByDistrict(int electionYear, int districtId) {
        List<Vote> votes = (electionYear == -1 && districtId == -1)
                ? voteRepo.findAll()
                : voteRepo.findAll().stream().filter(v ->
                (electionYear == -1 || v.getElectionId() == electionYear) &&
                        (districtId == -1 || v.getDistrictId() == districtId)).toList();

        Map<String, com.election.parlement.dto.ResultEntryDTO> resultMap = new LinkedHashMap<>();

        for (Vote v : votes) {
            String key = v.getElectionId() + ":" + v.getDistrictId();
            resultMap.putIfAbsent(key, new com.election.parlement.dto.ResultEntryDTO());
            com.election.parlement.dto.ResultEntryDTO dto = resultMap.get(key);
            dto.electionId = v.getElectionId();
            dto.districtId = v.getDistrictId();
            dto.totalVotes += v.getVotes();
            if (dto.partyVotes == null) dto.partyVotes = new ArrayList<>();

            com.election.parlement.dto.PartyVoteDTO pv = new com.election.parlement.dto.PartyVoteDTO();
            pv.electionId = v.getElectionId();
            pv.districtId = v.getDistrictId();
            pv.partyId = v.getPartyId();
            pv.votes = v.getVotes();
            dto.partyVotes.add(pv);
        }

        return new ArrayList<>(resultMap.values());
    }

    @Override
    public boolean updateResult(com.election.parlement.dto.ResultEntryDTO result) {
        voteRepo.deleteByElectionIdAndDistrictId(result.electionId, result.districtId);
        return saveResult(result);
    }

    @Override
    public boolean deleteResult(int electionId, int districtId) {
        voteRepo.deleteByElectionIdAndDistrictId(electionId, districtId);
        return true;
    }

    @Override
    public List<Integer> getPartiesByDistrict(int districtId) {
        return voteRepo.findByDistrictId(districtId).stream()
                .map(Vote::getPartyId)
                .distinct()
                .toList();
    }
}