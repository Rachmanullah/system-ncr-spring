package com.example.ncrsystem.ncrsystem.dto.department;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Data
public class DepartmentRequest {
    @NotBlank(message = "Department Name Required")
    @Length(max = 100)
    private String departmentName;
    private BigInteger status;
    private BigInteger deleted;
}
