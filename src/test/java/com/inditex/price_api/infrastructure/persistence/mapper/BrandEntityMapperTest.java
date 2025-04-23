package com.inditex.price_api.infrastructure.persistence.mapper;

import com.inditex.price_api.domain.model.Brand;
import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class BrandEntityMapperTest {

    private final BrandEntityMapper mapper = Mappers.getMapper(BrandEntityMapper.class);

    @Test
    void shouldMapBrandEntityToDomain() {
        BrandEntity entity = BrandEntity.builder()
                .id(1)
                .name("ZARA")
                .build();

        Brand brand = mapper.toDomain(entity);

        assertNotNull(brand);
        assertEquals(1, brand.id());
        assertEquals("ZARA", brand.name());
    }

    @Test
    void shouldReturnNullWhenEntityIsNull() {
        Brand brand = mapper.toDomain(null);
        assertNull(brand);
    }
}