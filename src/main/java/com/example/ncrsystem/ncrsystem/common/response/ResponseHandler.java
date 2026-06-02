package com.example.ncrsystem.ncrsystem.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static <T> ResponseEntity<ApiResponse<T>>
    success(T data) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        200,
                        "Success",
                        data
                )
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>>
    created(T data) {

        return ResponseEntity.status(
                        HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                201,
                                "Created",
                                data
                        )
                );
    }

    public static ResponseEntity<ApiResponse<Object>> error(
            HttpStatus status,
            Object message) {
        ApiResponse<Object> response =
                new ApiResponse<>(
                        status.value(),
                        message,
                        null
                );
        return new ResponseEntity<>(
                response,
                status
        );
    }
}
