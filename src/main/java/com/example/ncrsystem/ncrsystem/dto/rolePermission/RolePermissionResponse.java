package com.example.ncrsystem.ncrsystem.dto.rolePermission;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonPropertyOrder({
        "rolePermissionId",
        "permissionId",
        "permissionCode",
        "status"
})
public class RolePermissionResponse {
    private BigInteger rolePermissionId;
    private BigInteger permissionId;
    private String permissionCode;
    private Integer status;
}
