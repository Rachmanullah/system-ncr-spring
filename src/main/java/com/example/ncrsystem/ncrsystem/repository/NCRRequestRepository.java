package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.NCRRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface NCRRequestRepository extends JpaRepository<NCRRequest, BigInteger> {
    @Query(value = """
    SELECT COALESCE(
        MAX(CAST(RIGHT(NCR_NUMBER, 4) AS INTEGER)),
        0
    )
    FROM NCR_REQUEST_MAIN
    WHERE EXTRACT(YEAR FROM NCR_DATE) = :year
    """, nativeQuery = true)
    Integer getLastRunningNumber(@Param("year") Integer year);
    @Query("""
        SELECT u
        FROM NCRRequest u
        WHERE u.deleted = 0
    """)
    List<NCRRequest> findAllNcr();

    @Query("""
          SELECT u
          FROM NCRRequest u
          WHERE u.deleted = 0
          AND u.runningNumber > 0
    """)
    List<NCRRequest> findAllNcrNeedApprove();

    Optional<NCRRequest> findByNcrNumber(String ncrNumber);
}
