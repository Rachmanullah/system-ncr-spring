package com.example.ncrsystem.ncrsystem.service.category;


import com.example.ncrsystem.ncrsystem.dto.category.CategoryRequest;
import com.example.ncrsystem.ncrsystem.dto.category.CategoryResponse;

import java.math.BigInteger;
import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(BigInteger categoryId, CategoryRequest request);
    CategoryResponse delete(BigInteger categoryId);
}
