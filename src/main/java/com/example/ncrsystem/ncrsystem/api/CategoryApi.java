package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.category.CategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/category")
public interface CategoryApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@RequestBody CategoryRequest request);
    @PutMapping("/{categoryId}")
    ResponseEntity<?> update(@PathVariable("categoryId") Long id, @RequestBody CategoryRequest request);
    @DeleteMapping("/{categoryId}")
    ResponseEntity<?> delete(@PathVariable("categoryId") Long id);
}
