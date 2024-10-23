package com.example.curso.application.services;

import com.example.curso.application.ports.input.CategoryServicePort;
import com.example.curso.application.ports.output.CategoryPersistencePort;
import com.example.curso.domain.exceptions.CategoryNotFoundException;
import com.example.curso.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;

    @Override
    public Category findById(Long id) {
        return categoryPersistencePort.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryPersistencePort.findAll(pageable);
    }

    @Override
    public Category save(Category category) {
        return categoryPersistencePort.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        return categoryPersistencePort.findById(id)
                .map(categoryFound -> {
                    categoryFound.setName(category.getName());
                    return categoryPersistencePort.save(categoryFound);
                })
                .orElseThrow(CategoryNotFoundException::new);
    }
}
