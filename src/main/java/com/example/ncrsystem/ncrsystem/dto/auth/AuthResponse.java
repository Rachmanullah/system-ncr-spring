package com.example.ncrsystem.ncrsystem.dto.auth;

import com.example.ncrsystem.ncrsystem.dto.department.DepartmentResponse;
import com.example.ncrsystem.ncrsystem.dto.role.RoleResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonPropertyOrder({
        "userId",
        "username",
        "token"
})
public class AuthResponse {
    private BigInteger userId;
    private String username;
    private String token;
}