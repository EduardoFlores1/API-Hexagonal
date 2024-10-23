package com.example.curso.infraestructure.adapters.input.rest.model.response;

import com.example.curso.domain.utils.ProductStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private ProductStatus status;
}
