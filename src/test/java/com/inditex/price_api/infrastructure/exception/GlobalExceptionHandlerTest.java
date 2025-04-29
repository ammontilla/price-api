package com.inditex.price_api.infrastructure.exception;

import com.inditex.price_api.domain.port.input.GetPriceUseCase;
import com.inditex.price_api.domain.port.output.PriceRepositoryPort;
import com.inditex.price_api.infrastructure.exception.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    GetPriceUseCase priceService;
    @Mock
    PriceRepositoryPort mockRepo;

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();
    private final HttpServletRequest request = mock(HttpServletRequest.class);

    @Test
    void handleGeneric() {
        when(request.getRequestURI()).thenReturn("/test");

        Exception ex = new Exception("Unexpected error");
        ResponseEntity<ErrorResponse> response = handler.handleGeneric(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Unexpected error", response.getBody().getMessage());
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
        assertTrue(response.getBody().getMessage().contains("brandId: must not be null"));
        assertEquals("/prices", response.getBody().getPath());
    }

    @Test
    void shouldHandlePriceNotFoundException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        WebRequest request = mock(WebRequest.class);

        when(request.getDescription(false)).thenReturn("uri=/prices");

        PriceNotFoundException exception = new PriceNotFoundException("No price found for product 35455");

        ResponseEntity<ErrorResponse> response = handler.handleDomainNotFoundExceptions(exception, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Not Found", response.getBody().getError());
        assertEquals("No price found for product 35455", response.getBody().getMessage());
        assertEquals("/prices", response.getBody().getPath());
    }


}