package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.permissionAction.PermissionRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/settings/permission")
public interface PermissionApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody PermissionRequest request);
    @PutMapping("/{permissionId}")
    ResponseEntity<?> update(@PathVariable("permissionId") Long id,@Valid @RequestBody PermissionRequest request);
    @DeleteMapping("/{permissionId}")
    ResponseEntity<?> delete(@PathVariable("permissionId") Long id);
}
