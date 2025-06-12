package com.election.parlement.controller;

import com.election.parlement.dto.ResultEntryDTO;
import com.election.parlement.dto.PartyVoteDTO;
import com.election.parlement.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ResultController {

    @Autowired
    private ResultService service;

    @GetMapping("/election-year")
    public ResponseEntity<Map<String, Integer>> getElectionYear() {
        return ResponseEntity.ok(Map.of("year", service.getElectionYear()));
    }

    @PostMapping("/election-year")
    public ResponseEntity<Map<String, Boolean>> setElectionYear(@RequestBody ResultEntryDTO data) {
        return ResponseEntity.ok(Map.of("status", service.setElectionYear(data.electionId)));
    }

    @GetMapping("/results")
    public ResponseEntity<List<ResultEntryDTO>> getResults(@RequestParam(required = false) Integer electionYear,
                                                           @RequestParam(required = false) Integer districtId) {
        return ResponseEntity.ok(service.getResultsByDistrict(
                electionYear != null ? electionYear : -1,
                districtId != null ? districtId : -1));
    }

    @GetMapping("/districts/{districtId}/parties")
    public ResponseEntity<List<Integer>> getPartiesByDistrict(@PathVariable int districtId) {
        return ResponseEntity.ok(service.getPartiesByDistrict(districtId));
    }

    @PostMapping("/results")
    public ResponseEntity<Map<String, Boolean>> saveResult(@RequestBody ResultEntryDTO result) {
        if (!service.hasElectionYear()) return ResponseEntity.badRequest().body(Map.of("error", false));
        return ResponseEntity.ok(Map.of("status", service.saveResult(result)));
    }

    @PostMapping("/results/party-vote")
    public ResponseEntity<Map<String, Boolean>> savePartyVote(@RequestBody PartyVoteDTO vote) {
        if (!service.hasElectionYear()) return ResponseEntity.badRequest().body(Map.of("error", false));
        return ResponseEntity.ok(Map.of("partyVoteSaved", service.savePartyVote(vote)));
    }

    @PutMapping("/results")
    public ResponseEntity<Map<String, Boolean>> updateResult(@RequestBody ResultEntryDTO updated) {
        if (!service.hasElectionYear()) return ResponseEntity.badRequest().body(Map.of("error", false));
        return ResponseEntity.ok(Map.of("updated", service.updateResult(updated)));
    }

    @DeleteMapping("/results")
    public ResponseEntity<Map<String, Boolean>> deleteResult(@RequestParam int electionId,
                                                             @RequestParam int districtId) {
        if (!service.hasElectionYear()) return ResponseEntity.badRequest().body(Map.of("error", false));
        return ResponseEntity.ok(Map.of("deleted", service.deleteResult(electionId, districtId)));
    }
}
