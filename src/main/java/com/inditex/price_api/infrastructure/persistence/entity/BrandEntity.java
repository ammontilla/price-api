package com.inditex.price_api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BRANDS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;
}
