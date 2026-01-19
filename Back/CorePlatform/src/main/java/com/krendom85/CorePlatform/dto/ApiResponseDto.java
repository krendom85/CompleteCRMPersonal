package com.krendom85.CorePlatform.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDto<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponseDto<T> success(String message, T data) {
        ApiResponseDto<T> response = new ApiResponseDto<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> ApiResponseDto<T> failure(String message) {
        ApiResponseDto<T> response = new ApiResponseDto<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
    
    public static <T> ResponseEntity<ApiResponseDto<T>> success(T data, String message, HttpStatus status) {
        ApiResponseDto<T> response = new ApiResponseDto<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        return ResponseEntity.status(status).body(response);
    }
    
    public static <T> ResponseEntity<ApiResponseDto<T>> failed(T data, String message, HttpStatus status) {
        ApiResponseDto<T> response = new ApiResponseDto<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setData(data);
        return ResponseEntity.status(status).body(response);
    }
}
