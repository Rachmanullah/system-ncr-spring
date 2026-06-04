package com.example.ncrsystem.ncrsystem.dto.ncrrequest;

import com.example.ncrsystem.ncrsystem.dto.ncrrequestdetail.NCRDetailResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@JsonPropertyOrder({
        "ncrId",
        "ncrNumber",
        "ncrTitle",
        "ncrProject",
        "ncrDate",
        "requestorId",
        "requestorName",
        "departmentId",
        "departmentName",
        "implementationId",
        "implementationName",
        "statusCode",
        "statusName",
        "runningNumber",
        "ncrDetail"
})
public class NCRRequestResponse {
    private BigInteger ncrId;
    private String ncrNumber;
    private String ncrTitle;
    private String ncrProject;
    private Date ncrDate;
    private BigInteger requestorId;
    private String requestorName;
    private BigInteger departmentId;
    private String departmentName;
    private BigInteger implementationId;
    private String implementationName;
    private String statusCode;
    private String statusName;
    private Integer runningNumber;
    private NCRDetailResponse ncrDetail;
}
