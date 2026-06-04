package com.example.ncrsystem.ncrsystem.dto.ncrrequest;

import com.example.ncrsystem.ncrsystem.dto.ncrrequestdetail.NCRDetailRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class NCRRequestDto {
    @NotBlank(message = "NCR Title Required")
    @Length(max = 150)
    private String ncrTitle;

    private String ncrNumber;

    @Length(max=100)
    private String ncrProject;
    @NotNull(message = "NCR Date Required")
    private Date ncrDate;

    @NotNull(message = "Requestor Required")
    private BigInteger requestorId;

    @NotNull(message = "Department Required")
    private BigInteger departmentId;

    private BigInteger implementationId;

    @NotNull(message = "Action Required")
    private String action;
    private String statusCode;
    private String statusName;
    @Valid
    @NotNull(message = "Detail Required")
    private NCRDetailRequestDto detail;
}
