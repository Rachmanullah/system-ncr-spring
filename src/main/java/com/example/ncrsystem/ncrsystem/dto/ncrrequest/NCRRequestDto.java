package com.example.ncrsystem.ncrsystem.dto.ncrrequest;

import com.example.ncrsystem.ncrsystem.dto.ncrrequestdetail.NCRDetailRequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class NCRRequestDto {
    private String ncrNumber;

    @NotNull(message = "NCR Date Required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ncrDate;

    @NotBlank(message = "NCR Title Required")
    @Length(max = 150)
    private String ncrTitle;

    @Length(max=100)
    private String ncrProject;

    @NotNull(message = "Requestor Required")
    private BigInteger requestorId;

    @NotNull(message = "Department Required")
    private BigInteger departmentId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ncrImplementationDate;

    @NotNull(message = "Category Required")
    private String ncrCategory;

    private BigInteger implementationId;

    private String statusCode;
    private String statusName;
    private Integer runningNumber;
    @NotNull(message = "Action Required")
    private String action;

    @Min(0)
    private BigInteger approver;
    @Length(max= 150)
    private String approverNotes;
    @Valid
    @NotNull(message = "Detail Required")
    private NCRDetailRequestDto detail;
}
