package com.example.ncrsystem.ncrsystem.dto.rolePermission;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class RolePermissionRequest {
    private BigInteger rolePermissionId;
    private BigInteger roleId;
    private String permissionCode;
    private Integer status;
}
