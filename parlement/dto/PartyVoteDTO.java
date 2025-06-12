package com.election.parlement.dto;

import lombok.Data;

import java.util.List;
@Data
public class PartyVoteDTO {
    public int electionId;
    public int districtId;
    public int partyId;
    public int votes;
}

