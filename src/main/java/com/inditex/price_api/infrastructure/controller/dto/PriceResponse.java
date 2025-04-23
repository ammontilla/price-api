package com.inditex.price_api.infrastructure.controller.dto;


import com.inditex.price_api.domain.model.Price;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(
        @Schema(description = "Identificador del producto", example = "35455")
        Integer productId,
        @Schema(description = "Identificador de la marca", example = "1")
        Integer brandId,
        @Schema(description = "Identificador de la tarifa aplicable", example = "2")
        Integer priceList,
        @Schema(description = "Fecha de inicio de la tarifa", example = "2020-06-14T15:00:00")
        LocalDateTime startDate,
        @Schema(description = "Fecha de fin de la tarifa", example = "2020-12-31T23:59:59")
        LocalDateTime endDate,
        @Schema(description = "Precio final a aplicar", example = "25.45")
        BigDecimal price,
        @Schema(description = "Moneda", example = "EUR")
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