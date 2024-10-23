package com.example.curso.domain.models;

import com.example.curso.domain.utils.ProductStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private ProductStatus status;
}
