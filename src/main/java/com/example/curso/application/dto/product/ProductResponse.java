package com.example.curso.application.dto.product;

import com.example.curso.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String categoryName;
    private String name;
    private BigDecimal price;
    private StatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
