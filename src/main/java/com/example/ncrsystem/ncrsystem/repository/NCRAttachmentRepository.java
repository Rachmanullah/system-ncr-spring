package com.example.ncrsystem.ncrsystem.repository;

import com.example.ncrsystem.ncrsystem.model.NCRAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface NCRAttachmentRepository extends JpaRepository<NCRAttachment, BigInteger> {
    List<NCRAttachment> findByNcrRequest_NcrIdAndDeleted(BigInteger ncrId, BigInteger deleted);
    Optional<NCRAttachment> findByNcrAttachmentIdAndDeleted(BigInteger ncrAttachmentId, BigInteger deleted);
    List<NCRAttachment> findByNcrAttachmentIdInAndDeleted(List<BigInteger> ids, BigInteger deleted);
}
