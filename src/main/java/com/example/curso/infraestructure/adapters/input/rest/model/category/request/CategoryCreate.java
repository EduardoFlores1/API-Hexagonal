package com.example.curso.infraestructure.adapters.input.rest.model.category.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class CategoryCreate {
    @NotEmpty(message = "El nombre es requerido.")
    private String name;
}
