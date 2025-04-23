package com.inditex.price_api.domain.port.input;

import com.inditex.price_api.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
    Price getApplicablePrice(LocalDateTime applicationDate, Integer productId, Integer brandId);
}