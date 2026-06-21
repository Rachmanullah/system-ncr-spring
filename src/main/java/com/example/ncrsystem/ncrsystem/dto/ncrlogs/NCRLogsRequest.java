package com.example.ncrsystem.ncrsystem.dto.ncrlogs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Data
public class NCRLogsRequest {
    @NotNull(message = "NCR ID Required")
    private BigInteger ncrId;
    @NotNull(message = "User ID Required")
    private BigInteger userId;
    @Length(max= 100)
    private String statusName;
    @Length(max=100)
    private String notes;
    @Min(0)
    private Integer orderNumber;
}
