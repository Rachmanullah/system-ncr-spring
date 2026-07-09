package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.NCRAttachmentApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.common.util.FileStorageUtil;
import com.example.ncrsystem.ncrsystem.model.NCRAttachment;
import com.example.ncrsystem.ncrsystem.service.ncrattachment.NCRAttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
public class NCRAttachmentController implements NCRAttachmentApi {
    private final NCRAttachmentService ncrAttachmentService;
    private final FileStorageUtil fileStorageUtil;

    @Override
    public ResponseEntity<?> upload(MultipartFile file) {
        return ResponseHandler.success(ncrAttachmentService.upload(file));
    }

    @Override
    public ResponseEntity<Resource> download(Long id) {
        NCRAttachment attachment = ncrAttachmentService.findEntityById(BigInteger.valueOf(id));
        Resource resource = fileStorageUtil.loadAsResource(attachment.getFilePath());
        String contentType;
        try {
            contentType = Files.probeContentType(Paths.get(attachment.getFilePath()));
        } catch (IOException e) {
            contentType = null;
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getOriginalFileName() + "\"")
                .body(resource);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(ncrAttachmentService.deleteById(BigInteger.valueOf(id)));
    }
}
