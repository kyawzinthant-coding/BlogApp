package com.BlogPage.BlogApp.controller;

import com.BlogPage.BlogApp.Dto.CategoryDto;
import com.BlogPage.BlogApp.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    @Autowired
    public CategoryController (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Tag(name="Get all  categories", description = "Fetch all categories")
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categories =  categoryService.getAllCategory();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

}
