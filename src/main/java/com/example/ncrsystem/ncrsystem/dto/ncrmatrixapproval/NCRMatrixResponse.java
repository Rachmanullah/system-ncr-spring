package com.example.ncrsystem.ncrsystem.dto.ncrmatrixapproval;

import com.example.ncrsystem.ncrsystem.dto.department.DepartmentResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@JsonPropertyOrder({
        "ncrMatrixId",
        "ncrMatrixCode",
        "departmentId",
        "departmentCode",
        "departmentName",
        "approverId",
        "approverName",
        "ApproverPosition",
        "status"
})
public class NCRMatrixResponse {
    private BigInteger ncrMatrixId;
    private String ncrMatrixCode;
    private BigInteger departmentId;
    private String departmentCode;
    private String departmentName;
    private Integer status;
    private List<ApproverResponse> approver;

    @Data
    public static class ApproverResponse {
        private BigInteger approverId;
        private String approverName;
        private String approverPosition;
        private Integer orderNumber;
    }
}