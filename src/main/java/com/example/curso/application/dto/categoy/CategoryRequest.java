package com.example.curso.application.dto.categoy;

import com.example.curso.domain.enums.StatusEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryRequest {
    @NotEmpty(message = "El nombre es requerido.")
    private String name;
    @NotNull(message = "El estado es requerido")
    private StatusEnum status;
}
