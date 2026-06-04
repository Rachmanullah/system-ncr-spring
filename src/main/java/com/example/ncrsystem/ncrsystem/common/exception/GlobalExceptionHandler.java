package com.example.ncrsystem.ncrsystem.common.exception;

import com.example.ncrsystem.ncrsystem.common.response.ResponseHandler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseHandler.error(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));

        return ResponseHandler.error(
                HttpStatus.BAD_REQUEST,
                errors
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleBodyMissing(
            HttpMessageNotReadableException ex) {

        Map<String, String> errors = new LinkedHashMap<>();

        errors.put(
                "body",
                "Request body tidak boleh kosong"
        );

        return ResponseHandler.error(
                HttpStatus.BAD_REQUEST,
                errors
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(
            ConstraintViolationException ex) {

        Map<String, String> errors = new LinkedHashMap<>();

        for (ConstraintViolation<?> violation :
                ex.getConstraintViolations()) {

            String field = violation.getPropertyPath()
                    .toString();

            field = field.substring(
                    field.lastIndexOf(".") + 1
            );

            errors.put(
                    field,
                    violation.getMessage()
            );
        }

        return ResponseHandler.error(
                HttpStatus.BAD_REQUEST,
                errors
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {

        log.error("Unexpected Error", ex);

        return ResponseHandler.error(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage()
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(
            BadRequestException ex) {

        return ResponseHandler.error(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }
}
