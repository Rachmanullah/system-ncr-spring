package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.DepartmentApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.department.DepartmentRequest;
import com.example.ncrsystem.ncrsystem.service.department.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class DepartmentController implements DepartmentApi {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(departmentService.findAll());
    }

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody DepartmentRequest request) {
        return ResponseHandler.success(departmentService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id, @Valid @RequestBody DepartmentRequest request) {
        return ResponseHandler.success(departmentService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(departmentService.delete(BigInteger.valueOf(id)));
    }
}
