package com.inditex.price_api.infrastructure.persistence.mapper;

import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import com.inditex.price_api.infrastructure.persistence.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceEntityMapperTest {
    private final PriceEntityMapper mapper = Mappers.getMapper(PriceEntityMapper.class);

    @Test
    void shouldMapPriceEntityToDomain() {
        BrandEntity brand = BrandEntity.builder().id(1).name("ZARA").build();
        PriceEntity entity = PriceEntity.builder()
                .id(1L)
                .brand(brand)
                .productId(35455)
                .priceList(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .price(new BigDecimal("35.5"))
                .priority(0)
                .curr("EUR")
                .build();


        Price price = mapper.toDomain(entity);

        assertNotNull(price);
        assertEquals(35455, price.productId());
        assertEquals(1, price.brandId());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        Price price = mapper.toDomain(null);
        assertNull(price);
    }

    @Test
    void shouldReturnNullWhenBrandEntityIsNull() {
        PriceEntity entity = PriceEntity.builder()
                .id(1L)
                .brand(null)
                .productId(35455)
                .priceList(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(1))
                .price(new BigDecimal("35.5"))
                .priority(0)
                .curr("EUR")
                .build();


        Price price = mapper.toDomain(entity);
        assertNull(price.brandId());
    }
}