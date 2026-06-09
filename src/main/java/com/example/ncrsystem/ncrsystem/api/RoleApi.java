package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.role.RoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/master/roles")
public interface RoleApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@RequestBody RoleRequest request);
    @PutMapping("/{roleId}")
    ResponseEntity<?> update(@PathVariable("roleId") Long id, @RequestBody RoleRequest request);
    @DeleteMapping("/{roleId}")
    ResponseEntity<?> delete(@PathVariable("roleId") Long id);
}
