package com.example.ncrsystem.ncrsystem.service.role;

import com.example.ncrsystem.ncrsystem.dto.role.RoleRequest;
import com.example.ncrsystem.ncrsystem.dto.role.RoleResponse;

import java.math.BigInteger;
import java.util.List;

public interface RoleService {
    List<RoleResponse> findAll();
    RoleResponse create(RoleRequest request);
    RoleResponse update(BigInteger roleId, RoleRequest request);
    RoleResponse delete(BigInteger roleId);
}
