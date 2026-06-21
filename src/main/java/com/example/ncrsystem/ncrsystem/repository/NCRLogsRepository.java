package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.NCRLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface NCRLogsRepository extends JpaRepository<NCRLogs, BigInteger> {
    Optional<NCRLogs[]> findByNcrRequestNcrIdAndDeleted(
            BigInteger ncrId,
            BigInteger deleted
    );
}
