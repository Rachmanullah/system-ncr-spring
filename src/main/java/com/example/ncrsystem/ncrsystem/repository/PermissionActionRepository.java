package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.PermissionAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionActionRepository extends JpaRepository<PermissionAction, BigInteger> {
    List<PermissionAction> findByDeleted(BigInteger deleted);
    Optional<PermissionAction> findByPermissionCode(String permissionCode);
    Optional<PermissionAction> findByPermissionCodeAndPermissionIdNot(String permissionCode, BigInteger permissionId);
}
