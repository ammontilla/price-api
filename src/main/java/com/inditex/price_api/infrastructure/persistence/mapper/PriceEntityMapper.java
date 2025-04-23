package com.inditex.price_api.infrastructure.persistence.mapper;


import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.infrastructure.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {
    Price toDomain(PriceEntity entity);
}