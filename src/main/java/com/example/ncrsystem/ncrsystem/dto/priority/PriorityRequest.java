package com.example.ncrsystem.ncrsystem.dto.priority;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Data
public class PriorityRequest {
    @NotBlank(message = "Priority Code Required")
    @Length(max = 20)
    private String priorityCode;
    @Min(0)
    private Integer sla;
}
