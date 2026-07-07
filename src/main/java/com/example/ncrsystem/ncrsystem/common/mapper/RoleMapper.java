package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.role.RoleRequest;
import com.example.ncrsystem.ncrsystem.dto.role.RoleResponse;
import com.example.ncrsystem.ncrsystem.dto.rolePermission.RolePermissionResponse;
import com.example.ncrsystem.ncrsystem.model.Role;
import com.example.ncrsystem.ncrsystem.model.RolePermission;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

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
        response.setRolePermission(toRolePermissionResponses(role.getRolePermissions()));
        return response;
    }

    private List<RolePermissionResponse> toRolePermissionResponses(List<RolePermission> rolePermissions) {
        if (rolePermissions == null) {
            return Collections.emptyList();
        }
        return rolePermissions.stream()
                .map(this::toRolePermissionResponse)
                .toList();
    }
    private RolePermissionResponse toRolePermissionResponse(RolePermission rolePermission) {
        RolePermissionResponse response = new RolePermissionResponse();
        response.setRolePermissionId(rolePermission.getRolePermissionId());
        response.setPermissionId(rolePermission.getPermissionAction().getPermissionId());
        response.setPermissionCode(rolePermission.getPermissionAction().getPermissionCode());
        response.setStatus(
                rolePermission.getStatus() != null ? rolePermission.getStatus().intValue() : null
        );
        return response;
    }
}
