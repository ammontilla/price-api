package com.inditex.price_api.domain.port.output;

import com.inditex.price_api.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findPricesByCriteria(LocalDateTime applicationDate, Integer productId, Integer brandId);
}