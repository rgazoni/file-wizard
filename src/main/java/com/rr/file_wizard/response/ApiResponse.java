package com.rr.file_wizard.response;

import java.time.Instant;

public class ApiResponse<T> {
   private ApiStatus status;
   private T data;
   private String message;
   private Instant timestamp;

   public ApiResponse(ApiStatus status, T data, String message) {
       this.status = status;
       this.data = data;
       this.message = message;
       this.timestamp = Instant.now();
   }

    public static <T> ApiResponse<T> success(T data) {
       return new ApiResponse<>(ApiStatus.SUCCESS, data, null);
   }

   public static <T> ApiResponse<T> error(String message) {
       return new ApiResponse<>(ApiStatus.ERROR, null, message);
   }
}
