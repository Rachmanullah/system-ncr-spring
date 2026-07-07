package com.example.ncrsystem.ncrsystem.dto.permissionAction;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonPropertyOrder({
        "permissionId",
        "permissionCode",
        "permissionDescription",
        "isActive"
})
public class PermissionResponse {
    private BigInteger permissionId;
    private String permissionCode;
    private String permissionDescription;
    private BigInteger isActive;
}
