package com.example.ncrsystem.ncrsystem.service.department;

import com.example.ncrsystem.ncrsystem.dto.department.DepartmentRequest;
import com.example.ncrsystem.ncrsystem.dto.department.DepartmentResponse;

import java.math.BigInteger;
import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> findAll();
    DepartmentResponse create(DepartmentRequest request);
    DepartmentResponse update(BigInteger departmentId, DepartmentRequest request);
    DepartmentResponse delete(BigInteger departmentId);
}
