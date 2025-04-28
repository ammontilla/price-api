package com.inditex.price_api.infrastructure.persistence.adapter;

import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.domain.port.output.PriceRepositoryPort;
import com.inditex.price_api.infrastructure.exception.PriceNotFoundException;
import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import com.inditex.price_api.infrastructure.persistence.mapper.PriceEntityMapper;
import com.inditex.price_api.infrastructure.persistence.repository.BrandJpaRepository;
import com.inditex.price_api.infrastructure.persistence.repository.PriceJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final BrandJpaRepository brandJpaRepository;
    private final PriceJpaRepository jpaRepository;
    private final PriceEntityMapper mapper;

    public PriceRepositoryAdapter(BrandJpaRepository brandJpaRepository, PriceJpaRepository jpaRepository, PriceEntityMapper mapper) {
        this.brandJpaRepository = brandJpaRepository;
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Price> findPricesByCriteria(LocalDateTime applicationStartDate, Integer productId, Integer brandId) {
        BrandEntity brand = brandJpaRepository.findById(brandId)
                .orElseThrow(() -> new PriceNotFoundException("Brand not found"));

        return jpaRepository
                .findByBrandAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        brand, productId, applicationStartDate, applicationStartDate
                )
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}