package com.example.ncrsystem.ncrsystem.dto.category;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CategoryResponse {
    private BigInteger categoryId;
    private String categoryName;
}
