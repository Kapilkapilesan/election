package com.election.parlement.dto;

import lombok.Data;

import java.util.List;

@Data
public class PartyDTO {
    private String name;
    private String color;
    private String logo;
    private List<Integer> districts;


}