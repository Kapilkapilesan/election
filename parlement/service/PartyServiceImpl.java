package com.election.parlement.service;



import com.election.parlement.entity.Party;
import com.election.parlement.dto.PartyDTO;
import com.election.parlement.repo.PartyRepo;
import com.election.parlement.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyRepo repository;

    @Override
    public List<com.election.parlement.entity.Party> getAllParties() {
        return repository.findAll();
    }

    @Override
    public com.election.parlement.entity.Party getPartyById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Party createParty(PartyDTO dto) {
        Party party = new Party(dto.getName(), dto.getColor(), dto.getLogo(), dto.getDistricts());
        return repository.save(party);
    }

    @Override
    public Party updateParty(int id, PartyDTO dto) {
        Optional<Party> optional = repository.findById(id);
        if (optional.isPresent()) {
            Party party = optional.get();
            party.setName(dto.getName());
            party.setColor(dto.getColor());
            party.setLogo(dto.getLogo());
            party.setDistricts(dto.getDistricts());
            return repository.save(party);
        }
        return null;
    }

    @Override
    public boolean deleteParty(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Party> getAllPartyColors() {
        return repository.findAll()
                .stream()
                .map(party -> new Party(party.getName(), party.getColor(), null, null))
                .toList();
    }

    @Override
    public String getPartyColorById(int id) {
        Party party = repository.findById(id).orElse(null);
        return party != null ? party.getColor() : null;
    }

    @Override
    public Party createPartyColor(String color) {
        Party party = new Party(null, color, null, null);
        return repository.save(party);
    }

    @Override
    public boolean updatePartyColor(int id, String color) {
        Optional<Party> optional = repository.findById(id);
        if (optional.isPresent()) {
            Party party = optional.get();
            party.setColor(color);
            repository.save(party);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePartyColor(int id) {
        Optional<Party> optional = repository.findById(id);
        if (optional.isPresent()) {
            Party party = optional.get();
            party.setColor(null);
            repository.save(party);
            return true;
        }
        return false;
    }
}
