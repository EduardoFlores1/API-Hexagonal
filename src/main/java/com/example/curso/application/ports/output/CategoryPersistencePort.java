package com.example.curso.application.ports.output;

import com.example.curso.domain.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryPersistencePort {
    Optional<Category> findById(Long id);
    Page<Category> findAll(Pageable pageable);
    Category save(Category category);
}
