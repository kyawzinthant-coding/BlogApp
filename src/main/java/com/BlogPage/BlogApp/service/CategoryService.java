package com.BlogPage.BlogApp.service;

import com.BlogPage.BlogApp.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategory();
    CategoryDto createCategory(CategoryDto categoryDto);

    void DeleteCategory(Long categoryId);
}

