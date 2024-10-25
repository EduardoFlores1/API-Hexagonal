package com.example.curso.domain.models;

import com.example.curso.domain.utils.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private CategoryStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
