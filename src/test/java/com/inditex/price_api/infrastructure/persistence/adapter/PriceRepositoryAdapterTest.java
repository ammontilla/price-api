package com.inditex.price_api.infrastructure.persistence.adapter;

import com.inditex.price_api.domain.model.Brand;
import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.infrastructure.exception.BrandNotFoundException;
import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import com.inditex.price_api.infrastructure.persistence.entity.PriceEntity;
import com.inditex.price_api.infrastructure.persistence.mapper.PriceEntityMapper;
import com.inditex.price_api.infrastructure.persistence.repository.BrandJpaRepository;
import com.inditex.price_api.infrastructure.persistence.repository.PriceJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceRepositoryAdapterTest {


    private PriceJpaRepository priceJpaRepository;
    private BrandJpaRepository brandJpaRepository;
    private PriceEntityMapper priceEntityMapper;

    private PriceRepositoryAdapter adapter;

    @BeforeEach
    void setup() {
        priceJpaRepository = mock(PriceJpaRepository.class);
        brandJpaRepository = mock(BrandJpaRepository.class);
        priceEntityMapper = mock(PriceEntityMapper.class);
        adapter = new PriceRepositoryAdapter(brandJpaRepository, priceJpaRepository, priceEntityMapper);
    }

    @Test
    void shouldMapEntitiesToDomainCorrectly() {
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

        Price domainPrice = new Price(
                new Brand(1, "ZARA"),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurr()
        );

        when(brandJpaRepository.findById(1)).thenReturn(Optional.of(brand));
        when(priceJpaRepository.findByBrandAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                eq(brand), eq(35455), any(), any(), any()))
                .thenReturn(List.of(entity));
        when(priceEntityMapper.toDomain(entity)).thenReturn(domainPrice);

        List<Price> result = adapter.findPricesByCriteria(LocalDateTime.now(), 35455, 1);

        assertFalse(result.isEmpty());
        assertEquals(1, result.get(0).brandId());
        assertEquals("EUR", result.get(0).currency());
    }

    @Test
    void shouldThrowExceptionWhenBrandNotFound() {
        Integer brandId = 1;
        when(brandJpaRepository.findById(1)).thenReturn(Optional.empty());

        BrandNotFoundException ex = assertThrows(BrandNotFoundException.class, () -> {
            adapter.findPricesByCriteria(LocalDateTime.now(), 35455, brandId);
        });

        assertEquals("Brand not found with id: " + brandId, ex.getMessage());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        Price result = priceEntityMapper.toDomain(null);
        assertNull(result);
    }

    @Test
    void shouldReturnNullBrandIdWhenBrandIsNull() {
        PriceEntity entity = PriceEntity.builder()
                .id(1L)
                .brand(null)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(new BigDecimal("35.50"))
                .curr("EUR")
                .build();

        Price domainPrice = new Price(
                (Brand) null,
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurr()
        );

        when(priceEntityMapper.toDomain(entity)).thenReturn(domainPrice);

        Price price = priceEntityMapper.toDomain(entity);

        assertNotNull(price);
        assertNull(price.brand());
        assertNull(price.brandId());
        assertEquals("EUR", price.currency());
    }

}