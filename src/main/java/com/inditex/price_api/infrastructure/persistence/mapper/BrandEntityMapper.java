package com.inditex.price_api.infrastructure.persistence.mapper;


import com.inditex.price_api.domain.model.Brand;
import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {
    Brand toDomain(BrandEntity entity);
}