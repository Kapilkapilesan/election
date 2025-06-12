package com.election.parlement.controller;

import com.election.parlement.entity.Vote;
import com.election.parlement.repo.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
public class VoteController {
    @Autowired
    private VoteRepo voteRepo;

    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestBody Vote vote) {
        Vote savedVote = voteRepo.save(vote);
        return ResponseEntity.ok(savedVote);
    }
}

