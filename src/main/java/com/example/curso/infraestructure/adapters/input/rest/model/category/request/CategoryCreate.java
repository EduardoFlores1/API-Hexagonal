package com.example.curso.infraestructure.adapters.input.rest.model.category.request;

import com.example.curso.domain.utils.CategoryStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CategoryCreate {
    @NotEmpty(message = "El nombre es requerido.")
    private String name;
    private CategoryStatus status;
}
