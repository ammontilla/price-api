package com.inditex.price_api.domain.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BrandTest {

    @Test
    void shouldCreateBrandRecordCorrectly() {
        Brand brand = new Brand(1, "ZARA");

        assertEquals(1, brand.id());
        assertEquals("ZARA", brand.name());
    }

    @Test
    void shouldSupportEquality() {
        Brand brand1 = new Brand(1, "ZARA");
        Brand brand2 = new Brand(1, "ZARA");

        assertEquals(brand1, brand2);
        assertEquals(brand1.hashCode(), brand2.hashCode());
    }

    @Test
    void shouldBeUsableInHashSet() {
        Set<Brand> set = new HashSet<>();
        set.add(new Brand(1, "ZARA"));
        set.add(new Brand(1, "ZARA"));

        assertEquals(1, set.size());
    }

    @Test
    void shouldHaveReadableToString() {
        Brand brand = new Brand(2, "BERSHKA");
        String result = brand.toString();

        assertTrue(result.contains("2"));
        assertTrue(result.contains("BERSHKA"));
    }
}