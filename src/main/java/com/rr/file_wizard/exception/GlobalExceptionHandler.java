package com.rr.file_wizard.exception;

import com.rr.file_wizard.response.ApiResponse;
import com.rr.file_wizard.response.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileValidationException.class)
    public ResponseEntity<ApiResponse<String>> handleFileValidationException(FileValidationException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChecksumCalculationException.class)
    public ResponseEntity<ApiResponse<String>> handleChecksumCalculationException(ChecksumCalculationException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ApiResponse<String>> handleFileUploadException(FileUploadException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiResponse<String>> buildErrorResponse(String message, HttpStatus status) {
        ApiResponse<String> err = new ApiResponse<>(ApiStatus.ERROR, status.getReasonPhrase(), message);
        return new ResponseEntity<>(err, status);
    }
}
