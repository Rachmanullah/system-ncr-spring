package com.example.ncrsystem.ncrsystem.dto.rolemenu;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonPropertyOrder({
        "roleMenuId",
        "roleId",
        "roleName",
        "menuId",
        "menuTitle",
        "status"
})
public class RoleMenuResponse {
    private BigInteger roleMenuId;
    private BigInteger roleId;
    private String roleName;
    private BigInteger menuId;
    private String menuTitle;
    private BigInteger status;
}
