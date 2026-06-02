package com.example.ncrsystem.ncrsystem.dto.role;

import lombok.Data;

import java.math.BigInteger;

@Data
public class RoleResponse {
    private BigInteger roleId;
    private String roleName;
    private BigInteger status;
}
