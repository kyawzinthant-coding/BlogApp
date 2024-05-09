package com.BlogPage.BlogApp.service.impl;

import com.BlogPage.BlogApp.Dto.AuthorDto;
import com.BlogPage.BlogApp.model.Author;
import com.BlogPage.BlogApp.repository.AuthorRepository;
import com.BlogPage.BlogApp.service.AuthorService;
import com.BlogPage.BlogApp.util.ResponseTemplate.ApiResponse;
import com.BlogPage.BlogApp.util.ResponseTemplate.EntityMapper;
import com.BlogPage.BlogApp.util.ResponseTemplate.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private final AuthorRepository authorRepository;
    private final EntityMapper entityMapper;


    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, EntityMapper entityMapper) {
        this.authorRepository = authorRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<String>> createAuthor(AuthorDto authorDto) {
        try {
            logger.info("Author creation process start");
            Author author = entityMapper.mapDTOtoEntity(authorDto, new Author());
            authorRepository.save(author);
            logger.info("author create successfully");

            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "author created successfully", "Created");
        } catch (Exception e) {
            logger.error("error for now ");
        }
        return ResponseUtil.createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create user", "failed");
    }
}



