package com.inditex.price_api.infrastructure.persistence.repository;

import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import com.inditex.price_api.infrastructure.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {
    @Query("""
        SELECT p FROM PriceEntity p
        WHERE p.brand = :brand
          AND p.productId = :productId
          AND :applicationDate BETWEEN p.startDate AND p.endDate
        ORDER BY p.priority DESC
    """)
    List<PriceEntity> findApplicablePrices(
            @Param("brand") BrandEntity brand,
            @Param("productId") Integer productId,
            @Param("applicationDate") LocalDateTime applicationDate
    );
}