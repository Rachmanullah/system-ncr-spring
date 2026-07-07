package com.example.ncrsystem.ncrsystem.service.permission;

import com.example.ncrsystem.ncrsystem.dto.permissionAction.PermissionRequest;
import com.example.ncrsystem.ncrsystem.dto.permissionAction.PermissionResponse;

import java.math.BigInteger;
import java.util.List;

public interface PermissionService {
    List<PermissionResponse> findAll();
    PermissionResponse create(PermissionRequest request);
    PermissionResponse update(BigInteger permissionId, PermissionRequest request);
    PermissionResponse delete(BigInteger permissionId);
}
