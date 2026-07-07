package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.permissionAction.PermissionRequest;
import com.example.ncrsystem.ncrsystem.dto.permissionAction.PermissionResponse;
import com.example.ncrsystem.ncrsystem.model.PermissionAction;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public PermissionAction toEntity(PermissionRequest permissionRequest){
        PermissionAction permissionAction = new PermissionAction();
        permissionAction.setPermissionCode(permissionRequest.getPermissionCode());
        permissionAction.setPermissionDescription(permissionRequest.getPermissionDescription());
        permissionAction.setIsActive(permissionRequest.getIsActive());

        return permissionAction;
    }

    public PermissionResponse toResponse(PermissionAction permissionAction){
        PermissionResponse response = new PermissionResponse();
        response.setPermissionId(permissionAction.getPermissionId());
        response.setPermissionCode(permissionAction.getPermissionCode());
        response.setPermissionDescription(permissionAction.getPermissionDescription());
        response.setIsActive(permissionAction.getIsActive());

        return response;
    }
}
