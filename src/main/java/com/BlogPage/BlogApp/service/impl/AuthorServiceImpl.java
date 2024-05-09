package com.BlogPage.BlogApp.service.impl;

import com.BlogPage.BlogApp.Dto.AuthorDto;
import com.BlogPage.BlogApp.model.Author;
import com.BlogPage.BlogApp.repository.AuthorRepository;
import com.BlogPage.BlogApp.service.AuthorService;
import com.BlogPage.BlogApp.util.ResponseTemplate.ApiResponse;
import com.BlogPage.BlogApp.util.ResponseTemplate.EntityMapper;
import com.BlogPage.BlogApp.util.ResponseTemplate.ResponseUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ResponseEntity<ApiResponse<List<Author>>> getAuthors() {
        try{
            logger.info("Fetching all authors");
            List<Author> authors = authorRepository.findAll();
            logger.info("Fetched all authors");
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, " Authors fetched successfully" , authors);
        }catch(Exception e) {
            logger.error("Failed to fetch " + e.getMessage());
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failted to fetch authors", null);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Author>> getAuthor(Long authorId) {
        try{
            logger.info("Start fetching author");
            Author author = authorRepository.findById(authorId).orElseThrow(EntityExistsException::new);
            logger.info("Success");
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Author fetch successfully", author);
        }catch(Exception e) {
            logger.error("Failed to fetch " + e.getMessage());
            return ResponseUtil.createErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failted to fetch authors", null);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteAuthor(Long authorId) {
        try{
            authorRepository.deleteById(authorId);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK , "Author Deleted successfully", "delete");
        }catch(EmptyResultDataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, " Author not found" , null);
        }catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete author" , null);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Author>> updateAuthor(Long authorId , AuthorDto authorDto){
        try {
            Author existingAuthor = authorRepository
                    .findById(authorId)
                    .orElseThrow(()->new EntityNotFoundException("Author not found with ID " + authorId));

            entityMapper.mapDTOtoEntity(existingAuthor, authorDto);
            authorRepository.save(existingAuthor);
            return ResponseUtil.createSuccessResponse(
                    HttpStatus.OK , "Author update successfully" , existingAuthor
            );
        }catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update user" ,null);
        }
    }

}



