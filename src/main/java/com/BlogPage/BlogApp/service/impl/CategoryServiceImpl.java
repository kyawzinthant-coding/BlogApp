package com.BlogPage.BlogApp.service.impl;

import com.BlogPage.BlogApp.Dto.CategoryDto;
import com.BlogPage.BlogApp.model.Category;
import com.BlogPage.BlogApp.repository.CategoryRepository;
import com.BlogPage.BlogApp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameAlreadyBoundException;
import java.util.List;
import java.util.Optional;
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

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDto.getName());

        if(optionalCategory.isPresent()) {
            try {
                throw new NameAlreadyBoundException("Name already exist");
            } catch (NameAlreadyBoundException e) {
                throw new RuntimeException(e);
            }
        }

        Category category= modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);

        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public void DeleteCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            categoryRepository.delete(existingCategory);
        } else {
            throw new IllegalArgumentException("Category with ID " + categoryId + " not found");
        }
    }

}
