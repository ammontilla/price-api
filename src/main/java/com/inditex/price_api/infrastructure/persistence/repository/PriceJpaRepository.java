package com.inditex.price_api.infrastructure.persistence.repository;

import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import com.inditex.price_api.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findByBrandAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            BrandEntity brand,
            Integer productId,
            LocalDateTime applicationStartDate,
            LocalDateTime applicationEndDate,
            Sort sort
    );
}