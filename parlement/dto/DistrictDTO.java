package com.election.parlement.dto;

import lombok.Data;

@Data
public class DistrictDTO {
    private Long id;
    private String name;
    private Long provinceId;
    private int allocatedSeats;
}

