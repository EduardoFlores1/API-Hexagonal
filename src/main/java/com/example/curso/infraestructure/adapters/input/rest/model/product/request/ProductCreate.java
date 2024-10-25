package com.example.curso.infraestructure.adapters.input.rest.model.product.request;

import com.example.curso.domain.utils.ProductStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
public class ProductCreate {
    @NotEmpty(message = "El nombre es requerido.")
    private String name;
    @NotNull(message = "El precio es requerido.")
    @Min(value = 1, message = "El precio debe ser igual o mayor a 1.")
    private BigDecimal price;
    private ProductStatus status;
    @Min(value = 1 ,message = "El id de la categoría es igual o mayor a 1 y es requerida.")
    private Long categoryId;
}
