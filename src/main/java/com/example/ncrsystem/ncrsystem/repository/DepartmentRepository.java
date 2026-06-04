package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, BigInteger> {
    @Query("""
        SELECT u
        FROM Department u
        WHERE u.deleted = 0
    """)
    List<Department> findAllDepartment();
}
