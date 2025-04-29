package com.inditex.price_api.infrastructure.controller;

import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.domain.port.input.GetPriceUseCase;
import com.inditex.price_api.infrastructure.controller.dto.PriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@Tag(name = "Price API", description = "Operaciones para obtener precios aplicables por producto y marca")
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    public PriceController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @Operation(summary = "Obtener precio aplicable para un producto, marca y fecha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Precio encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró un precio aplicable"),
            @ApiResponse(responseCode = "400", description = "Solicitud malformada o inválida")
    })
    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam("applicationDate") @Parameter(example = "2020-06-14T16:00:00") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") @Parameter(example = "35455") Integer productId,
            @RequestParam("brandId") @Parameter(example = "1") Integer brandId
    ) {
        Price price = this.getPriceUseCase.getApplicablePrice(applicationDate, productId, brandId);
        return ResponseEntity.ok(PriceResponse.from(price));
    }
}