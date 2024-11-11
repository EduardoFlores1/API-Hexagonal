package com.example.curso.application.ports.input;

import com.example.curso.application.dto.categoy.CategoryRequest;
import com.example.curso.application.dto.categoy.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryServicePort {
    CategoryResponse findById(Long id);
    Page<CategoryResponse> findAll(Pageable pageable);
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id, CategoryRequest request);
    void disabledById(Long categoryId);
}
