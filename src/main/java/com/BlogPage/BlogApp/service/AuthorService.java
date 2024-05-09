package com.BlogPage.BlogApp.service;

import com.BlogPage.BlogApp.Dto.AuthorDto;
import com.BlogPage.BlogApp.model.Author;
import com.BlogPage.BlogApp.util.ResponseTemplate.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {

    ResponseEntity<ApiResponse<String>> createAuthor(AuthorDto authorDto);

    ResponseEntity<ApiResponse<List<Author>>> getAuthors();
    ResponseEntity<ApiResponse<Author>> getAuthor(Long authorId);
}
