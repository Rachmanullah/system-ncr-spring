package com.example.ncrsystem.ncrsystem.dto.role;

import com.example.ncrsystem.ncrsystem.dto.rolePermission.RolePermissionRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;
import java.util.List;

@Data
public class RoleRequest {
    @NotBlank(message = "Rolename Required")
    @Length(max = 20)
    private String roleName;
    private BigInteger status;
    private BigInteger deleted;
    private List<RolePermissionRequest> rolePermission;
}
