package com.example.ncrsystem.ncrsystem.service.role;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.mapper.RoleMapper;
import com.example.ncrsystem.ncrsystem.dto.role.RoleRequest;
import com.example.ncrsystem.ncrsystem.dto.role.RoleResponse;
import com.example.ncrsystem.ncrsystem.dto.rolePermission.RolePermissionRequest;
import com.example.ncrsystem.ncrsystem.model.PermissionAction;
import com.example.ncrsystem.ncrsystem.model.Role;
import com.example.ncrsystem.ncrsystem.model.RolePermission;
import com.example.ncrsystem.ncrsystem.repository.PermissionActionRepository;
import com.example.ncrsystem.ncrsystem.repository.RolePermissionRepository;
import com.example.ncrsystem.ncrsystem.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleLmpl implements RoleService{
    private final RoleRepository repository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionActionRepository permissionActionRepository;
    private final RoleMapper mapper;

    public RoleLmpl(RoleRepository repository, RolePermissionRepository rolePermissionRepository, PermissionActionRepository permissionActionRepository, RoleMapper mapper) {
        this.repository = repository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.permissionActionRepository = permissionActionRepository;
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
    @Transactional
    public RoleResponse create(RoleRequest request) {
        Role role = mapper.toEntity(request);
        role = repository.save(role);
        List<RolePermission> permissions = buildAllActionsForNewRole(role, request.getRolePermission());
        rolePermissionRepository.saveAll(permissions);

        role.setRolePermissions(permissions);
        return mapper.toResponse(role);
    }

    @Override
    @Transactional
    public RoleResponse update(BigInteger roleId, RoleRequest request) {
        Role role = repository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setRoleName(request.getRoleName());
        role.setStatus(request.getStatus());
        repository.save(role);

        Role refreshed = repository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        updateRolePermissions(roleId, request.getRolePermission());
        return mapper.toResponse(refreshed);
    }

    @Override
    @Transactional
    public RoleResponse delete(BigInteger roleId) {
        Role role = repository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setDeleted(StatusConstant.DELETED);
        repository.save(role);
        return mapper.toResponse(role);
    }

    private List<RolePermission> buildAllActionsForNewRole(Role role, List<RolePermissionRequest> requestedPermissions) {
        Map<BigInteger, BigInteger> statusByAction = new HashMap<>();
        if (requestedPermissions != null) {
            for (RolePermissionRequest req : requestedPermissions) {
                if (req.getRolePermissionId() != null && req.getStatus() != null) {
                    statusByAction.put(req.getRolePermissionId(), BigInteger.valueOf(req.getStatus()));
                }
            }
        }

        List<RolePermission> permissions = new ArrayList<>();
        List<PermissionAction> permissionActions = permissionActionRepository.findByDeleted(BigInteger.ZERO);
        for (PermissionAction permissionAction : permissionActions) {
            BigInteger status = statusByAction.getOrDefault(permissionAction.getPermissionId(), BigInteger.ONE);
            RolePermission permission = RolePermission.builder()
                    .role(role)
                    .permissionAction(permissionAction)
                    .status(status)
                    .deleted(BigInteger.ZERO)
                    .build();
            permissions.add(permission);
        }
        return permissions;
    }

    private void updateRolePermissions(BigInteger roleId, List<RolePermissionRequest> rolePermissionRequests) {
        if (rolePermissionRequests == null || rolePermissionRequests.isEmpty()) {
            return;
        }
        for (RolePermissionRequest permRequest : rolePermissionRequests) {
            if (!roleId.equals(permRequest.getRoleId())) {
                throw new RuntimeException(
                        "Role permission " + permRequest.getRolePermissionId()
                                + " tidak dimiliki oleh role " + roleId
                );
            }
            RolePermission rolePermission = rolePermissionRepository
                    .findById(permRequest.getRolePermissionId())
                    .orElseThrow(() -> new RuntimeException(
                            "Role permission not found: " + permRequest.getRolePermissionId()));

            if (permRequest.getStatus() != null) {
                rolePermission.setStatus(BigInteger.valueOf(permRequest.getStatus()));
            }
            rolePermissionRepository.save(rolePermission);
        }
    }
}
