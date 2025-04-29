package com.inditex.price_api.infrastructure.exception;

import com.inditex.price_api.infrastructure.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String NOT_FOUND = "Not Found";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    private static final String VALIDATION_ERROR = "Validation Error";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(getBuildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(" | "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(getBuildErrorResponse(HttpStatus.BAD_REQUEST, VALIDATION_ERROR, message, request.getRequestURI())
                );
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePriceNotFoundException(PriceNotFoundException ex, WebRequest request) {
        log.error("Price not found: {}", ex.getMessage());

        ErrorResponse errorResponse = getBuildErrorResponse(HttpStatus.NOT_FOUND, NOT_FOUND, ex.getMessage(), request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private static ErrorResponse getBuildErrorResponse(HttpStatus internalServerError, String internalServerError1, String ex, String request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(internalServerError.value())
                .error(internalServerError1)
                .message(ex)
                .path(request)
                .build();
    }
}
