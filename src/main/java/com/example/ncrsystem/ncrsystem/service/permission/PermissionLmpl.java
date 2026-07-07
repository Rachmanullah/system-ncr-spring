package com.example.ncrsystem.ncrsystem.service.permission;

import com.example.ncrsystem.ncrsystem.common.mapper.PermissionMapper;
import com.example.ncrsystem.ncrsystem.dto.permissionAction.PermissionRequest;
import com.example.ncrsystem.ncrsystem.dto.permissionAction.PermissionResponse;
import com.example.ncrsystem.ncrsystem.model.PermissionAction;
import com.example.ncrsystem.ncrsystem.repository.PermissionActionRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionLmpl implements PermissionService{
    private final PermissionActionRepository permissionActionRepository;
    private final PermissionMapper permissionMapper;
    @Override
    public List<PermissionResponse> findAll() {
        return permissionActionRepository.findByDeleted(BigInteger.ZERO).stream()
                .map(permissionMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public PermissionResponse create(PermissionRequest request) {
        if (permissionActionRepository.findByPermissionCode(request.getPermissionCode()).isPresent()) {
            throw new IllegalArgumentException(
                    "Permission code ready exists: " + request.getPermissionCode());
        }

        PermissionAction permissionAction = permissionMapper.toEntity(request);
        permissionAction = permissionActionRepository.save(permissionAction);
        return permissionMapper.toResponse(permissionAction);
    }

    @Override
    @Transactional
    public PermissionResponse update(BigInteger permissionId, PermissionRequest request) {
        PermissionAction permissionAction = permissionActionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionId));
        if (BigInteger.ONE.equals(permissionAction.getDeleted())) {
            throw new RuntimeException("Permission deleted: " + permissionId);
        }
        if (request.getPermissionCode() != null
                && !request.getPermissionCode().equals(permissionAction.getPermissionCode())) {

            permissionActionRepository
                    .findByPermissionCodeAndPermissionIdNot(request.getPermissionCode(), permissionId)
                    .ifPresent(existing -> {
                        throw new IllegalArgumentException(
                                "Permission code already exist: " + request.getPermissionCode());
                    });

            permissionAction.setPermissionCode(request.getPermissionCode());
        }
        permissionAction.setPermissionDescription(request.getPermissionDescription());
        permissionAction.setIsActive(request.getIsActive());
        permissionAction = permissionActionRepository.save(permissionAction);
        return permissionMapper.toResponse(permissionAction);
    }

    @Override
    @Transactional
    public PermissionResponse delete(BigInteger permissionId) {
        PermissionAction permissionAction = permissionActionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionId));
        if (BigInteger.ONE.equals(permissionAction.getDeleted())) {
            return permissionMapper.toResponse(permissionAction);
        }
        permissionAction.setDeleted(BigInteger.ONE);
        permissionAction.setIsActive(BigInteger.ZERO);
        permissionAction = permissionActionRepository.save(permissionAction);
        return permissionMapper.toResponse(permissionAction);
    }
}
