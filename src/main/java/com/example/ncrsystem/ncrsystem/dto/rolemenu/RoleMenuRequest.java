package com.example.ncrsystem.ncrsystem.dto.rolemenu;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigInteger;

@Data
public class RoleMenuRequest {
    @NotNull(message = "Role Required")
    @Min(1)
    private BigInteger roleId;
    @NotNull(message = "Menu Required")
    @Min(1)
    private BigInteger menuId;
    private Integer status;
}
