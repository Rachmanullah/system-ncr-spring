package com.example.ncrsystem.ncrsystem.api;

import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/ncr/attachment")
public interface NCRAttachmentApi {
    @PostMapping(consumes = "multipart/form-data")
    ResponseEntity<?> upload(@RequestPart("file") MultipartFile file);
    @GetMapping("/{attachmentId}/download")
    ResponseEntity<Resource> download(@PathVariable("attachmentId") Long id);
    @DeleteMapping("/{attachmentId}")
    ResponseEntity<?> delete(@PathVariable("attachmentId") Long id);
}
