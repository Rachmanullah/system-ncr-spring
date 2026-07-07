package com.example.ncrsystem.ncrsystem.dto.permissionAction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class PermissionRequest {
    @NotBlank(message = "Permission Code Required")
    private String permissionCode;
    private String permissionDescription;
    private BigInteger isActive;
    private BigInteger deleted;
}
