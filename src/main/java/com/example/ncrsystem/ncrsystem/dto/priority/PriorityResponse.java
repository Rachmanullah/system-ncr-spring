package com.example.ncrsystem.ncrsystem.dto.priority;

import lombok.Data;

import java.math.BigInteger;

@Data
public class PriorityResponse {
    private BigInteger priorityId;
    private String priorityCode;
    private Integer sla;
}
