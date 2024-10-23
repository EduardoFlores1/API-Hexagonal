package com.example.curso.infraestructure.adapters.input.rest.model.product.response;

import com.example.curso.domain.utils.ProductStatus;
import com.example.curso.infraestructure.adapters.input.rest.model.category.response.CategoryResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private ProductStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CategoryResponse category;
}
