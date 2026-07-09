package com.example.ncrsystem.ncrsystem.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileStorageUtil {
    @Value("${app.attachment.temp-dir}")
    private String tempDir;
    public String storeTempFile(MultipartFile file) {
        try {
            Path dirPath = Paths.get(tempDir).toAbsolutePath().normalize();
            Files.createDirectories(dirPath);

            String originalName = file.getOriginalFilename();
            String extension = "";
            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }
            String storedFileName = UUID.randomUUID() + extension;

            Path targetPath = dirPath.resolve(storedFileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return targetPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage(), e);
        }
    }

    public Resource loadAsResource(String filePath) {
        try {
            Path path = Paths.get(filePath).toAbsolutePath().normalize();
            Resource resource = new UrlResource(path.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("File not found or not readable: " + filePath);
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to load file: " + e.getMessage(), e);
        }
    }

    public void deletePhysicalFile(String filePath) {
        try {
            if (filePath != null) {
                Files.deleteIfExists(Paths.get(filePath));
            }
        } catch (IOException e) {
            // log saja, jangan sampai gagal delete file mem-block proses lain
            System.err.println("Failed to delete file: " + filePath);
        }
    }
}
