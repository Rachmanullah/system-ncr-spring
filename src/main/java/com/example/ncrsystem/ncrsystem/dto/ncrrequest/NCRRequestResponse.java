package com.example.ncrsystem.ncrsystem.dto.ncrrequest;

import com.example.ncrsystem.ncrsystem.dto.ncrlogs.NCRLogsResponse;
import com.example.ncrsystem.ncrsystem.dto.ncrrequestdetail.NCRDetailResponse;
import com.example.ncrsystem.ncrsystem.model.NCRLogs;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonPropertyOrder({
        "ncrId",
        "ncrNumber",
        "ncrDate",
        "ncrTitle",
        "ncrProject",
        "requestorId",
        "requestorName",
        "departmentId",
        "departmentName",
        "ncrImplementationDate",
        "ncrCategory",
        "implementationId",
        "implementationName",
        "statusCode",
        "statusName",
        "runningNumber",
        "ncrDetail",
        "ncrLogs"
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
    private Date ncrImplementationDate;
    private String ncrCategory;
    private BigInteger implementationId;
    private String implementationName;
    private String statusCode;
    private String statusName;
    private Integer runningNumber;
    private NCRDetailResponse ncrDetail;
    private List<NCRLogsResponse> ncrLogs;
}
