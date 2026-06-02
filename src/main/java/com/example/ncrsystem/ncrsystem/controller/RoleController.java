package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.RoleApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.role.RoleRequest;
import com.example.ncrsystem.ncrsystem.service.role.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class RoleController implements RoleApi {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(roleService.findAll());
    }

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody RoleRequest request) {
        return ResponseHandler.success(roleService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id,@Valid @RequestBody RoleRequest request) {
        return ResponseHandler.success(roleService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(roleService.delete(BigInteger.valueOf(id)));
    }
}
