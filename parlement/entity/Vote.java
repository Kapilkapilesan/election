package com.election.parlement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int electionId;
    private int districtId;
    private int partyId;
    private int votes;


}