package com.election.parlement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "current_election_year")
public class ElectionYear {
    @Id
    private int id = 1;
    private int year;


}
