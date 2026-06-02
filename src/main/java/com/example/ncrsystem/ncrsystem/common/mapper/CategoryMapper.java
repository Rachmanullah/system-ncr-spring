package com.example.ncrsystem.ncrsystem.common.mapper;

import com.example.ncrsystem.ncrsystem.dto.category.CategoryRequest;
import com.example.ncrsystem.ncrsystem.dto.category.CategoryResponse;
import com.example.ncrsystem.ncrsystem.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        return category;
    }

    public CategoryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }
        CategoryResponse response = new CategoryResponse();
        response.setCategoryId(category.getCategoryId());
        response.setCategoryName(category.getCategoryName());
        return response;
    }
}
