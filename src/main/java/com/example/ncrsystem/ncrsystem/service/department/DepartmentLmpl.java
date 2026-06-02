package com.example.ncrsystem.ncrsystem.service.department;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.DepartmentMapper;
import com.example.ncrsystem.ncrsystem.dto.department.DepartmentRequest;
import com.example.ncrsystem.ncrsystem.dto.department.DepartmentResponse;
import com.example.ncrsystem.ncrsystem.model.Department;
import com.example.ncrsystem.ncrsystem.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class DepartmentLmpl implements DepartmentService{
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    public DepartmentLmpl(DepartmentRepository repository, DepartmentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DepartmentResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    @Override
    public DepartmentResponse create(DepartmentRequest request) {
        Department department = mapper.toEntity(request);
        department = repository.save(department);
        return mapper.toResponse(department);
    }

    @Override
    public DepartmentResponse update(BigInteger departmentId, DepartmentRequest request) {
        Department department = repository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
        department.setDepartmentName(request.getDepartmentName());
        department.setStatus(request.getStatus());
        repository.save(department);
        return mapper.toResponse(department);
    }
    @Override
    public DepartmentResponse delete(BigInteger departmentId) {
        Department department = repository.findById(departmentId).orElseThrow(() -> new RuntimeException("Department not found"));
        department.setDeleted(StatusConstant.DELETED);
        repository.save(department);
        return mapper.toResponse(department);
    }
}
