package com.inditex.price_api.infrastructure.controller.dto;


import com.inditex.price_api.domain.model.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(
        Integer productId,
        Integer brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String currency
) {
    public static PriceResponse from(Price price) {
        return new PriceResponse(
                price.productId(),
                price.brandId(),
                price.priceList(),
                price.startDate(),
                price.endDate(),
                price.price(),
                price.currency()
        );
    }
}