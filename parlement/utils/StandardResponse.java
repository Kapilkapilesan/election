package com.election.parlement.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandardResponse {
    private String status;
    private String message;
    private Object data;
    private int statusCode;
}