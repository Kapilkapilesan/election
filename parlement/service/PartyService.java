package com.election.parlement.service;

import com.election.parlement.dto.PartyDTO;
import com.election.parlement.entity.Party;

import java.util.List;

public interface PartyService {
    List<Party> getAllParties();
    Party getPartyById(int id);
    Party createParty(PartyDTO dto);
    Party updateParty(int id, PartyDTO dto);
    boolean deleteParty(int id);
    List<Party> getAllPartyColors();
    String getPartyColorById(int id);
    Party createPartyColor(String color);
    boolean updatePartyColor(int id, String color);
    boolean deletePartyColor(int id);
}