package com.inditex.price_api.infrastructure.exception;

import com.inditex.price_api.infrastructure.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {


    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();
    private final HttpServletRequest request = mock(HttpServletRequest.class);


    @Test
    void handleRuntime() {
        when(request.getRequestURI()).thenReturn("/test");

        RuntimeException ex = new RuntimeException("Test error");
        ResponseEntity<ErrorResponse> response = handler.handleRuntime(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test error", response.getBody().message());
        assertEquals("/test", response.getBody().path());
    }

    @Test
    void handleGeneric() {
        when(request.getRequestURI()).thenReturn("/test");

        Exception ex = new Exception("Unexpected error");
        ResponseEntity<ErrorResponse> response = handler.handleGeneric(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Unexpected error", response.getBody().message());
    }

    @Test
    void handleValidation() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/prices");

        FieldError fieldError = new FieldError("priceRequest", "brandId", "must not be null");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<ErrorResponse> response = handler.handleValidation(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().message().contains("brandId: must not be null"));
        assertEquals("/prices", response.getBody().path());
    }
}