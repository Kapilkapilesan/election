package com.election.parlement.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResultEntryDTO {
    public int electionId;
    public int districtId;
    public int totalVotes;
    public List<PartyVoteDTO> partyVotes;
}

