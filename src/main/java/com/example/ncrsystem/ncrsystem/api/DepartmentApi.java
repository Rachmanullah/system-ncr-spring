package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.department.DepartmentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/departments")
public interface DepartmentApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@RequestBody DepartmentRequest request);
    @PutMapping("/{departmentId}")
    ResponseEntity<?> update(@PathVariable("departmentId") Long id, @RequestBody DepartmentRequest request);
    @DeleteMapping("/{departmentId}")
    ResponseEntity<?> delete(@PathVariable("departmentId") Long id);
}
