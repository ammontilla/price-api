package com.inditex.price_api.infrastructure.persistence.mapper;


import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.infrastructure.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {
    @Mappings({
            @Mapping(source = "brand", target = "brand"),
            @Mapping(source = "curr", target = "currency")
    })
    Price toDomain(PriceEntity entity);
}