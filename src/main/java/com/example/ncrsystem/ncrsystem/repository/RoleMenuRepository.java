package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, BigInteger> {
    @Query("""
        SELECT u
        FROM RoleMenu u
        WHERE u.deleted = 0
    """)
    List<RoleMenu> findAllRoleMenu();

    @Query("""
        SELECT u
        from RoleMenu u
        WHERE u.role.roleId = :roleId
        AND u.deleted = 0
    """)
    List<RoleMenu> findRoleMenuByRoleId(@Param("roleId") BigInteger roleId);
}
