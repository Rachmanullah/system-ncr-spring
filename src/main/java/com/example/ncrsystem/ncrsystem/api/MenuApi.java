package com.example.ncrsystem.ncrsystem.api;

import com.example.ncrsystem.ncrsystem.dto.menu.MenuRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/layout/menu")
public interface MenuApi {
    @GetMapping
    ResponseEntity<?> findAll();
    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody MenuRequest request);
    @PutMapping("/{menuId}")
    ResponseEntity<?> update(@PathVariable("menuId") Long id,@Valid @RequestBody MenuRequest request);
    @DeleteMapping("/{menuId}")
    ResponseEntity<?> delete(@PathVariable("menuId") Long id);
}
