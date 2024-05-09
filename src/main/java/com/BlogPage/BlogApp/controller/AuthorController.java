package com.BlogPage.BlogApp.controller;

import com.BlogPage.BlogApp.Dto.AuthorDto;
import com.BlogPage.BlogApp.service.impl.AuthorServiceImpl;
import com.BlogPage.BlogApp.util.ResponseTemplate.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/author")
public class AuthorController {

    private final Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorServiceImpl authorServiceImpl;

    @Autowired
    public AuthorController(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @Tag(name="Create Author", description = "Create Author End Point")
    @PostMapping("/")
    public ResponseEntity<ApiResponse<String>> createAuthor(@Valid @RequestBody AuthorDto authorDto){
        return authorServiceImpl.createAuthor(authorDto);
    }


}
