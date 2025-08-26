package com.deliverytech.delivery.Utils;

import java.time.Instant;

public class ApiResponseCustom<T> {
    private boolean success;
    private T data;
    private String message;
    private Instant timestamp;

    public ApiResponseCustom(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

}
