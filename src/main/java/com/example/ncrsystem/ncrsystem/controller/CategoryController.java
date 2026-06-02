package com.example.ncrsystem.ncrsystem.controller;

import com.example.ncrsystem.ncrsystem.api.CategoryApi;
import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import com.example.ncrsystem.ncrsystem.dto.category.CategoryRequest;
import com.example.ncrsystem.ncrsystem.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class CategoryController implements CategoryApi {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseHandler.success(categoryService.findAll());
    }

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody CategoryRequest request) {
        return ResponseHandler.success(categoryService.create(request));
    }

    @Override
    public ResponseEntity<?> update(Long id, @Valid @RequestBody CategoryRequest request) {
        return ResponseHandler.success(categoryService.update(BigInteger.valueOf(id), request));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return ResponseHandler.success(categoryService.delete(BigInteger.valueOf(id)));
    }
}
