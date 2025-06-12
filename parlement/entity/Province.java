package com.election.parlement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import com.election.parlement.entity.District;

import java.util.List;

@Entity
@Data
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true , nullable = false)
    private String name;

    @Column(name = "district_count", nullable = false)
    private int districtCount;

    public Province() {}

    public Province(int  id , String name, int districtCount) {
        this.id =  id;
        this.name = name;
        this.districtCount = districtCount;
    }

}
