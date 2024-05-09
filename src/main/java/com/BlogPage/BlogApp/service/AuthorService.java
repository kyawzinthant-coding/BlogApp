package com.BlogPage.BlogApp.service;

import com.BlogPage.BlogApp.Dto.AuthorDto;
import com.BlogPage.BlogApp.util.ResponseTemplate.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthorService {

    ResponseEntity<ApiResponse<String>> createAuthor(AuthorDto authorDto);

}
