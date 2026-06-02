package com.example.ncrsystem.ncrsystem.service.role;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.RoleMapper;
import com.example.ncrsystem.ncrsystem.dto.role.RoleRequest;
import com.example.ncrsystem.ncrsystem.dto.role.RoleResponse;
import com.example.ncrsystem.ncrsystem.model.Role;
import com.example.ncrsystem.ncrsystem.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class RoleLmpl implements RoleService{
    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleLmpl(RoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public List<RoleResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    @Override
    public RoleResponse create(RoleRequest request) {
        Role role = mapper.toEntity(request);
        role = repository.save(role);
        return mapper.toResponse(role);
    }

    @Override
    public RoleResponse update(BigInteger roleId, RoleRequest request) {
        Role role = repository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setRoleName(request.getRoleName());
        role.setStatus(request.getStatus());
        repository.save(role);
        return mapper.toResponse(role);
    }

    @Override
    public RoleResponse delete(BigInteger roleId) {
        Role role = repository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setDeleted(StatusConstant.DELETED);
        repository.save(role);
        return mapper.toResponse(role);
    }
}
