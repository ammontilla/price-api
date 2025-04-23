package com.inditex.price_api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        BigDecimal price,
        String currency
) {
}
