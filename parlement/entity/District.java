package com.election.parlement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @Column(name = "allocated_seats", nullable = false)
    private int allocatedSeats;

    public District() {}

    public District(int id, String name, Province province, int allocatedSeats) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.allocatedSeats = allocatedSeats;
    }

}
