package com.inditex.price_api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
        Brand brand,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        BigDecimal price,
        String currency
) {

    public Price(Integer brandId, LocalDateTime startDate, LocalDateTime endDate,
                 Integer priceList, Integer productId, Integer priority,
                 BigDecimal price, String currency) {
        this(new Brand(brandId, null), startDate, endDate, priceList, productId, priority, price, currency);
    }

    public Integer brandId() {
        return brand != null ? brand.id() : null;
    }
}
