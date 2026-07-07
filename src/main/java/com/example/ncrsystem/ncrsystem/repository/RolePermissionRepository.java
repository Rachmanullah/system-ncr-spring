package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, BigInteger> {
}
