package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.NCRRequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface NCRRequestDetailRepository extends JpaRepository<NCRRequestDetail, BigInteger> {
    Optional<NCRRequestDetail> findByNcrRequest_NcrId(BigInteger ncrId);
}
