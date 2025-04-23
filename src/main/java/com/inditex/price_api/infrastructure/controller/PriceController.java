package com.inditex.price_api.infrastructure.controller;

import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.domain.port.input.GetPriceUseCase;
import com.inditex.price_api.infrastructure.controller.dto.PriceResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    public PriceController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam Integer productId,
            @RequestParam Integer brandId
    ) {
        Price price = getPriceUseCase.getApplicablePrice(applicationDate, productId, brandId);
        return ResponseEntity.ok(PriceResponse.from(price));
    }
}