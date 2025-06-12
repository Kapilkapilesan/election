package com.election.parlement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "parties")
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;
    private String color;
    private String logo;

    @ElementCollection
    @CollectionTable(name = "party_districts", joinColumns = @JoinColumn(name = "party_id"))
    @Column(name = "district_id")
    private List<Integer> districts;

    // Constructors
    public Party() {}

    public Party(String name, String color, String logo, List<Integer> districts) {
        this.name = name;
        this.color = color;
        this.logo = logo;
        this.districts = districts;
    }


}