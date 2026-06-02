package com.example.ncrsystem.ncrsystem.dto.department;

import lombok.Data;

import java.math.BigInteger;

@Data
public class DepartmentResponse {
    private BigInteger departmentId;
    private String departmentName;
    private BigInteger status;
}
