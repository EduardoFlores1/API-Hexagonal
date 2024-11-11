package com.example.curso.infraestructure.adapters.output.persistence.repository;

import com.example.curso.infraestructure.adapters.output.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
