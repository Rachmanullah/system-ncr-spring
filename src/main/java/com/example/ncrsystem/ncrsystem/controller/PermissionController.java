package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.PermissionApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.permissionAction.PermissionRequest;
import com.example.ncrsystem.ncrsystem.service.permission.PermissionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;

@RestController
@AllArgsConstructor
public class PermissionController implements PermissionApi {
    private final PermissionService permissionService;

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(permissionService.findAll());
    }

    @Override
    public ResponseEntity<?> create(@Valid PermissionRequest request) {
        return ResponseHandler.success(permissionService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id,@Valid PermissionRequest request) {
        return ResponseHandler.success(permissionService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(permissionService.delete(BigInteger.valueOf(id)));
    }
}
