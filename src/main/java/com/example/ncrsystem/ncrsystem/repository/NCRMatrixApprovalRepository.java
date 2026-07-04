package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.NCRMatrixApproval;
import com.example.ncrsystem.ncrsystem.model.NCRMatrixDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface NCRMatrixApprovalRepository extends JpaRepository<NCRMatrixApproval, BigInteger> {
    @Query(value = """
    SELECT COALESCE(
        MAX(
            substring(NCR_MATRIX_CODE from '[^-]+$')::integer
        ),
        0
    )
    FROM NCR_MATRIX_APPROVAL
    WHERE split_part(NCR_MATRIX_CODE, '-', 3)::integer = :year
      AND NCR_MATRIX_CODE LIKE CONCAT('NCR-', UPPER(:deptCode), '-%')
    """, nativeQuery = true)
    Integer getLastRunningNumber(
            @Param("year") Integer year,
            @Param("deptCode") String deptCode
    );
    List<NCRMatrixApproval> findByDeleted(
            BigInteger deleted
    );
    Optional<NCRMatrixApproval>
    findByNcrMatrixIdAndDeleted(
            BigInteger id,
            BigInteger deleted
    );
    Optional<NCRMatrixApproval>
    findByDepartmentDepartmentIdAndDeleted(
            BigInteger departmentId,
            BigInteger deleted
    );

}
