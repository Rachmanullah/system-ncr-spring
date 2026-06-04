package com.example.ncrsystem.ncrsystem.dto.ncrrequestdetail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Data
public class NCRDetailRequestDto {
    @NotBlank(message = "Description Required")
    @Length(max = 4000)
    private String description;
    @NotNull(message = "Priority Required")
    private BigInteger priorityId;
    @Length(max = 4000)
    private String asIs;
    @Length(max = 4000)
    private String toBe;
    @Length(max = 4000)
    private String benefit;
    @Length(max = 4000)
    private String impact;
    @Length(max = 4000)
    private String financialImpact;
}
