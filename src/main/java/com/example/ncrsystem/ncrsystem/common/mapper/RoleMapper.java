package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.role.RoleRequest;
import com.example.ncrsystem.ncrsystem.dto.role.RoleResponse;
import com.example.ncrsystem.ncrsystem.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toEntity(RoleRequest request) {
        Role role = new Role();
        role.setRoleName(request.getRoleName());
        role.setStatus(request.getStatus());
        return role;
    }

    public RoleResponse toResponse(Role role) {
        if (role == null) {
            return null;
        }
        RoleResponse response = new RoleResponse();
        response.setRoleId(role.getRoleId());
        response.setRoleName(role.getRoleName());
        response.setStatus(role.getStatus());
        return response;
    }
}
