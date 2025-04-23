package com.inditex.price_api.infrastructure.persistence.repository;

import com.inditex.price_api.infrastructure.persistence.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BrandJpaRepositoryTest {

    @Autowired
    private BrandJpaRepository brandJpaRepository;

    @Test
    void shouldFindExistingBrandById() {
        Optional<BrandEntity> found = brandJpaRepository.findById(1);
        assertTrue(found.isPresent());
        assertEquals("ZARA", found.get().getName());
    }

    @Test
    void shouldReturnEmptyIfNotFound() {
        Optional<BrandEntity> found = brandJpaRepository.findById(999);
        assertTrue(found.isEmpty());
    }
}
