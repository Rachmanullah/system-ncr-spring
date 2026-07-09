package com.example.ncrsystem.ncrsystem.service.ncrattachment;

import com.example.ncrsystem.ncrsystem.dto.ncrattachment.NCRAttachmentResponse;
import com.example.ncrsystem.ncrsystem.model.NCRAttachment;
import com.example.ncrsystem.ncrsystem.model.NCRRequest;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.List;

public interface NCRAttachmentService {
    NCRAttachmentResponse upload(MultipartFile file);
    List<NCRAttachmentResponse> findByNcrId(BigInteger ncrId);
    NCRAttachment findEntityById(BigInteger ncrAttachmentId);
    void linkAttachmentsToNcr(List<BigInteger> attachmentIds, NCRRequest ncrRequest);
    NCRAttachmentResponse deleteById(BigInteger ncrAttachmentId);
}
