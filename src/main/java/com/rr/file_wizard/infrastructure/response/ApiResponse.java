package com.rr.file_wizard.infrastructure.response;

import java.time.Instant;

public record ApiResponse<T>(String status, T data, String message, Instant timestamp) {

    public ApiResponse(String status, T data, String message) {
        this(status, data, message, Instant.now());
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Success", data, null);
    }

    public static <T> ApiResponse<T> error(String status, String message) {
        return new ApiResponse<>(status, null, message);
    }
}