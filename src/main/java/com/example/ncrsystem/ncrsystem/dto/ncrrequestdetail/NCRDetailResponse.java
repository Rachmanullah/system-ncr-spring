package com.example.ncrsystem.ncrsystem.dto.ncrrequestdetail;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
@JsonPropertyOrder({
        "ncrDetailId",
        "description",
        "priorityId",
        "asIs",
        "toBe",
        "benefit",
        "impact",
        "financialImpact"
})
public class NCRDetailResponse {
    private BigInteger ncrDetailId;
    private String description;
    private BigInteger priorityId;
    private String asIs;
    private String toBe;
    private String benefit;
    private String impact;
    private String financialImpact;
}
