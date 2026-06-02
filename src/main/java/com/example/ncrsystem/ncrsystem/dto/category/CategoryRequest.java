package com.example.ncrsystem.ncrsystem.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Data
public class CategoryRequest {
    @NotBlank(message = "Category name Required")
    @Length(max = 20)
    private String categoryName;
}
