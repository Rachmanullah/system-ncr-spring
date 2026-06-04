package com.example.ncrsystem.ncrsystem.dto.department;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonPropertyOrder({
        "departmentId",
        "departmentCode",
        "departmentName",
        "status"
})
public class DepartmentResponse {
    private BigInteger departmentId;
    private String departmentCode;
    private String departmentName;
    private BigInteger status;
}
