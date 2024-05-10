package com.BlogPage.BlogApp.service.impl;

import com.BlogPage.BlogApp.Dto.CategoryDto;
import com.BlogPage.BlogApp.model.Category;
import com.BlogPage.BlogApp.repository.CategoryRepository;
import com.BlogPage.BlogApp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository , ModelMapper modelMapper){
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category -> modelMapper.map(category,CategoryDto.class))).collect(Collectors.toList());
    }

}
