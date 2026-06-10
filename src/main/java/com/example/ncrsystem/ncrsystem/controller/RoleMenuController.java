package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.RoleMenuApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.rolemenu.RoleMenuRequest;
import com.example.ncrsystem.ncrsystem.service.rolemenu.RoleMenuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class RoleMenuController implements RoleMenuApi {
    private final RoleMenuService roleMenuService;

    public RoleMenuController(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(roleMenuService.findAll());
    }

    @Override
    public ResponseEntity<?> findByRoleId(Long roleId) {
        return ResponseHandler.success(roleMenuService.findByRoleId(BigInteger.valueOf(roleId)));
    }

    @Override
    public ResponseEntity<?> create(@Valid  @RequestBody RoleMenuRequest request) {
        return ResponseHandler.success(roleMenuService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id, @Valid @RequestBody RoleMenuRequest request) {
        return ResponseHandler.success(roleMenuService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(roleMenuService.delete(BigInteger.valueOf(id)));
    }
}
