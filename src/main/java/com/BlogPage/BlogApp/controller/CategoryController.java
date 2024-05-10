package com.BlogPage.BlogApp.controller;

import com.BlogPage.BlogApp.Dto.CategoryDto;
import com.BlogPage.BlogApp.service.CategoryService;
import com.BlogPage.BlogApp.util.ResponseTemplate.ApiResponse;
import com.BlogPage.BlogApp.util.ResponseTemplate.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
        List<CategoryDto> categoryDtos = categoryService.getAllCategory();
        return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Success", categoryDtos);
    }

    @Tag(name="Create New Category")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.createCategory(categoryDto);
        return ResponseUtil.createErrorResponse(HttpStatus.OK, "Success", categoryDto1);
    }

    @Tag(name="Delete new Category")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long categoryId){
        categoryService.DeleteCategory(categoryId);
        return  ResponseUtil.createSuccessResponse(HttpStatus.OK, "Success" , "Deleted");
    }

}
