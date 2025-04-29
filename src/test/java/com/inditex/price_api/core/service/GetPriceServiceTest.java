package com.inditex.price_api.core.service;

import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.domain.port.output.PriceRepositoryPort;
import com.inditex.price_api.infrastructure.exception.PriceNotFoundException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GetPriceServiceTest {

    @Test
    void shouldReturnHighestPriorityPrice() {
        PriceRepositoryPort mockRepo = mock(PriceRepositoryPort.class);
        GetPriceService service = new GetPriceService(mockRepo);

        Price lowPriority = new Price(1, now(), now(), 1, 35455, 0, BigDecimal.valueOf(35.5), "EUR");
        Price highPriority = new Price(1, now(), now(), 2, 35455, 1, BigDecimal.valueOf(25.45), "EUR");

        when(mockRepo.findPricesByCriteria(any(), any(), any()))
                .thenReturn(List.of(lowPriority, highPriority));

        Price result = service.getApplicablePrice(now(), 35455, 1);
        assertEquals(2, result.priceList());
        assertEquals(BigDecimal.valueOf(25.45), result.price());
    }

    @Test
    void shouldThrowExceptionWhenNoPriceFound() {
        PriceRepositoryPort mockRepo = mock(PriceRepositoryPort.class);
        GetPriceService service = new GetPriceService(mockRepo);

        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 123;
        Integer brandId = 1;

        when(mockRepo.findPricesByCriteria(any(), any(), any()))
                .thenReturn(Collections.emptyList());

        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> service.getApplicablePrice(applicationDate, productId, brandId));

        assertEquals(String.format("No price found for product %d, brand %d at %s", productId, brandId, applicationDate)
                , exception.getMessage());
    }


    private LocalDateTime now() {
        return LocalDateTime.of(2020, 6, 14, 16, 0, 0);
    }
}