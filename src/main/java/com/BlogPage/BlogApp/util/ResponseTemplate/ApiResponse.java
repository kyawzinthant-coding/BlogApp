package com.BlogPage.BlogApp.util.ResponseTemplate;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {
    private HttpStatus header;
    private String message;
    private T data;

    public ApiResponse(HttpStatus httpStatus, String message, T data) {
    }
}
