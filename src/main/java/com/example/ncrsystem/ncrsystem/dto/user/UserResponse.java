package com.example.ncrsystem.ncrsystem.dto.user;

import com.example.ncrsystem.ncrsystem.dto.department.DepartmentResponse;
import com.example.ncrsystem.ncrsystem.dto.role.RoleResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonPropertyOrder({
        "userId",
        "username",
        "fullname",
        "email",
        "position",
        "department",
        "role",
        "status",
})
public class UserResponse {
    private BigInteger userId;
    private String username;
    private String fullname;
    private String email;
    private DepartmentResponse department;
    private RoleResponse role;
    private String position;
    private BigInteger status;
}
