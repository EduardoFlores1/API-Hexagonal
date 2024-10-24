package com.example.curso.application.ports.input;

import com.example.curso.domain.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryServicePort {
    Category findById(Long id);
    Page<Category> findAll(Pageable pageable);
    Category create(Category category);
    Category update(Long id, Category category);
}
