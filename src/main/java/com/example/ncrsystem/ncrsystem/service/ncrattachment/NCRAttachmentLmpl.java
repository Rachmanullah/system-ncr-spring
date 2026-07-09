package com.example.ncrsystem.ncrsystem.service.ncrattachment;

import com.example.ncrsystem.ncrsystem.common.constant.StatusConstant;
import com.example.ncrsystem.ncrsystem.common.util.FileStorageUtil;
import com.example.ncrsystem.ncrsystem.dto.ncrattachment.NCRAttachmentResponse;
import com.example.ncrsystem.ncrsystem.model.NCRAttachment;
import com.example.ncrsystem.ncrsystem.model.NCRRequest;
import com.example.ncrsystem.ncrsystem.repository.NCRAttachmentRepository;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class NCRAttachmentLmpl implements NCRAttachmentService{
    private final NCRAttachmentRepository ncrAttachmentRepository;
    private final FileStorageUtil fileStorageUtil;

    private final long maxSizeMb = 10;

    @Override
    public NCRAttachmentResponse upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        long maxBytes = maxSizeMb * 1024 * 1024;
        if (file.getSize() > maxBytes) {
            throw new RuntimeException("File size exceeds limit of " + maxSizeMb + "MB");
        }

        String storedPath = fileStorageUtil.storeTempFile(file);

        NCRAttachment attachment = NCRAttachment.builder()
                .fileName(storedPath.substring(storedPath.lastIndexOf(java.io.File.separator) + 1))
                .originalFileName(file.getOriginalFilename())
                .filePath(storedPath)
                .fileSize(file.getSize())
                .deleted(StatusConstant.ACTIVE)
                .created(new java.sql.Date(System.currentTimeMillis()))
                .build();

        NCRAttachment saved = ncrAttachmentRepository.save(attachment);
        return toResponse(saved);
    }

    @Override
    public List<NCRAttachmentResponse> findByNcrId(BigInteger ncrId) {
        return ncrAttachmentRepository
                .findByNcrRequest_NcrIdAndDeleted(ncrId, StatusConstant.ACTIVE)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public NCRAttachment findEntityById(BigInteger ncrAttachmentId) {
        return ncrAttachmentRepository
                .findByNcrAttachmentIdAndDeleted(ncrAttachmentId, StatusConstant.ACTIVE)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
    }

    @Override
    public void linkAttachmentsToNcr(List<BigInteger> attachmentIds, NCRRequest ncrRequest) {
        if (attachmentIds == null || attachmentIds.isEmpty()) {
            return;
        }
        List<NCRAttachment> attachments = ncrAttachmentRepository
                .findByNcrAttachmentIdInAndDeleted(attachmentIds, StatusConstant.ACTIVE);

        for (NCRAttachment attachment : attachments) {
            attachment.setNcrRequest(ncrRequest);
        }
        ncrAttachmentRepository.saveAll(attachments);
    }

    @Override
    public NCRAttachmentResponse deleteById(BigInteger ncrAttachmentId) {
        NCRAttachment attachment = ncrAttachmentRepository
                .findByNcrAttachmentIdAndDeleted(ncrAttachmentId, StatusConstant.ACTIVE)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
        attachment.setDeleted(StatusConstant.DELETED);
        ncrAttachmentRepository.save(attachment);
        return toResponse(attachment);
        // opsional: hapus juga file fisiknya sekarang, atau lewat scheduled cleanup job
        // fileStorageUtil.deletePhysicalFile(attachment.getFilePath());
    }

    private NCRAttachmentResponse toResponse(NCRAttachment attachment) {
        return NCRAttachmentResponse.builder()
                .ncrAttachmentId(attachment.getNcrAttachmentId())
                .ncrId(attachment.getNcrRequest() != null ? attachment.getNcrRequest().getNcrId() : null)
                .fileName(attachment.getFileName())
                .originalFileName(attachment.getOriginalFileName())
                .fileSize(attachment.getFileSize())
                .created(attachment.getCreated())
                .build();
    }
}
