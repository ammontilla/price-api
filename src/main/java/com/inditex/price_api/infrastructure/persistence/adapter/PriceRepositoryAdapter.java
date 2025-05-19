package com.inditex.price_api.infrastructure.persistence.adapter;

import com.inditex.price_api.domain.model.Price;
import com.inditex.price_api.domain.port.output.PriceRepositoryPort;
import com.inditex.price_api.infrastructure.exception.BrandNotFoundException;
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
    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper mapper;

    public PriceRepositoryAdapter(BrandJpaRepository brandJpaRepository, PriceJpaRepository priceJpaRepository, PriceEntityMapper mapper) {
        this.brandJpaRepository = brandJpaRepository;
        this.priceJpaRepository = priceJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Price> findPricesByCriteria(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        BrandEntity brand = this.brandJpaRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found with id: " + brandId));

        return this.priceJpaRepository
                .findApplicablePrices(
                        brand, productId, applicationDate
                )
                .stream()
                .map(this.mapper::toDomain)
                .toList();
    }
}