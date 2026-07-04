package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.NCRMatrixApproval;
import com.example.ncrsystem.ncrsystem.model.NCRMatrixDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface NCRMatrixDetailRepository extends JpaRepository<NCRMatrixDetail, BigInteger> {
    Optional<NCRMatrixDetail>
    findByNcrMatrixDetailIdAndDeleted(
            BigInteger id,
            BigInteger deleted
    );
    List<NCRMatrixDetail> findByNcrMatrixApproval(
            NCRMatrixApproval ncrMatrixApproval
    );

    void deleteByNcrMatrixApproval(
            NCRMatrixApproval ncrMatrixApproval
    );
    Optional<NCRMatrixDetail>
    findFirstByNcrMatrixApproval_NcrMatrixIdAndDeletedOrderByOrderNumberAsc(
            BigInteger matrixId,
            BigInteger deleted
    );
}
