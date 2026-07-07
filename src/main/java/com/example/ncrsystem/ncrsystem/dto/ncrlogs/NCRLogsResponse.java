package com.example.ncrsystem.ncrsystem.dto.ncrlogs;

import com.example.ncrsystem.ncrsystem.dto.ncrrequest.NCRRequestResponse;
import com.example.ncrsystem.ncrsystem.dto.user.UserResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@JsonPropertyOrder({
        "ncrLogId",
        "orderNumber",
        "userId",
        "user",
        "statusName",
        "notes",
        "date"
})
public class NCRLogsResponse {
    private BigInteger ncrLogId;
    private BigInteger userId;
    private String username;
    private String userPosition;
    private String statusName;
    private String notes;
    private Integer orderNumber;
    private Date date;
}
