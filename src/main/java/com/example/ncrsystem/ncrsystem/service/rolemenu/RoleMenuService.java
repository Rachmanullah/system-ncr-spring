package com.example.ncrsystem.ncrsystem.service.rolemenu;

import com.example.ncrsystem.ncrsystem.dto.rolemenu.RoleMenuRequest;
import com.example.ncrsystem.ncrsystem.dto.rolemenu.RoleMenuResponse;

import java.math.BigInteger;
import java.util.List;

public interface RoleMenuService {
    List<RoleMenuResponse> findAll();
    List<RoleMenuResponse> findByRoleId(BigInteger roleId);
    RoleMenuResponse create(RoleMenuRequest roleMenuRequest);
    RoleMenuResponse update(BigInteger roleMenuId, RoleMenuRequest roleMenuRequest);
    RoleMenuResponse delete(BigInteger roleMenuId);
}
