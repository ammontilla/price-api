package com.inditex.price_api.infrastructure.persistence.repository;

import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandJpaRepository extends JpaRepository<BrandEntity, Integer> {
}
