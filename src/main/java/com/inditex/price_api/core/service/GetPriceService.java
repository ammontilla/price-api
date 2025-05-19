package com.inditex.price_api.core.service;


import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.domain.port.input.GetPriceUseCase;
import com.inditex.price_api.domain.port.output.PriceRepositoryPort;
import com.inditex.price_api.infrastructure.exception.PriceNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class GetPriceService implements GetPriceUseCase {

    private final PriceRepositoryPort repository;

    public GetPriceService(PriceRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "prices", key = "{#applicationDate, #productId, #brandId}")
    public Price getApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return repository.findPricesByCriteria(applicationDate, productId, brandId).stream()
                .max(Comparator.comparingInt(Price::priority))
                .orElseThrow(() -> new PriceNotFoundException(
                        String.format("No price found for product %d, brand %d at %s", productId, brandId, applicationDate)
                ));
    }
}