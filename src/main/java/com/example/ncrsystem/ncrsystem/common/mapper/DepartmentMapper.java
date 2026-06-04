package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.department.DepartmentRequest;
import com.example.ncrsystem.ncrsystem.dto.department.DepartmentResponse;
import com.example.ncrsystem.ncrsystem.model.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public Department toEntity(DepartmentRequest request) {
        Department department = new Department();
        department.setDepartmentCode(request.getDepartmentCode());
        department.setDepartmentName(request.getDepartmentName());
        department.setStatus(request.getStatus());
        return department;
    }

    public DepartmentResponse toResponse(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentResponse response = new DepartmentResponse();
        response.setDepartmentId(department.getDepartmentId());
        response.setDepartmentCode(department.getDepartmentCode());
        response.setDepartmentName(department.getDepartmentName());
        response.setStatus(department.getStatus());
        return response;
    }
}
