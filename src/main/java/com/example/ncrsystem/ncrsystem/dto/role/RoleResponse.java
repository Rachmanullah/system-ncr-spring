package com.example.ncrsystem.ncrsystem.dto.role;

import com.example.ncrsystem.ncrsystem.dto.rolePermission.RolePermissionResponse;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class RoleResponse {
    private BigInteger roleId;
    private String roleName;
    private BigInteger status;
    private List<RolePermissionResponse> rolePermission;
}
