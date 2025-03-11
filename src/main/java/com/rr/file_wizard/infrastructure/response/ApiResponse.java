package com.rr.file_wizard.infrastructure.response;

import java.time.Instant;

public class ApiResponse<T> {
   private String status;
   private T data;
   private String message;
   private Instant timestamp;

   public ApiResponse(String status, T data, String message) {
       this.status = status;
       this.data = data;
       this.message = message;
       this.timestamp = Instant.now();
   }

    public static <T> ApiResponse<T> success(T data) {
       return new ApiResponse<>("Success", data, null);
   }

   public static <T> ApiResponse<T> error(String status, String message) {
      return new ApiResponse<>(status, null, message);
   }

    public String getStatus() { return status; }
    public T getData() { return data; }
    public String getMessage() { return message; }
    public Instant getTimestamp() { return timestamp; }
}
