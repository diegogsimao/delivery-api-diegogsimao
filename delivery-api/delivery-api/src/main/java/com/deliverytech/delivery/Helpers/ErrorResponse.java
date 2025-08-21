package com.deliverytech.delivery.Helpers;

import java.time.Instant;

public class ErrorResponse {
    private boolean success;
    private ErrorDetails error;
    private Instant timestamp;

    public ErrorResponse(String code, String message, String details) {
        this.success = false;
        this.error = new ErrorDetails(code, message, details);
        this.timestamp = Instant.now();
    }

    // Getters e Setters

    public static class ErrorDetails {
        private String code;
        private String message;
        private String details;

        public ErrorDetails(String code, String message, String details) {
            this.code = code;
            this.message = message;
            this.details = details;
        }
        
        // Getters e Setters
    }
}
