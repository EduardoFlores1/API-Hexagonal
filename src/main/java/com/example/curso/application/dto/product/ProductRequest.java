package com.example.curso.application.dto.product;

import com.example.curso.domain.enums.StatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductRequest {
    @NotEmpty(message = "El nombre es requerido.")
    private String name;
    @NotNull(message = "El precio es requerido.")
    @Min(value = 1, message = "El precio debe ser igual o mayor a 1.")
    private BigDecimal price;
    @NotNull(message = "El estado es requerido")
    private StatusEnum status;
    @NotNull(message = "La categoriaId no debe ser nula")
    private Long categoryId;
}
