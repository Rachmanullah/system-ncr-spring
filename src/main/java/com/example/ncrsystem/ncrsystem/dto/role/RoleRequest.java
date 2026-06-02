package com.example.ncrsystem.ncrsystem.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Data
public class RoleRequest {
    @NotBlank(message = "Rolename Required")
    @Length(max = 20)
    private String roleName;
    private BigInteger status;
    private BigInteger deleted;
}
