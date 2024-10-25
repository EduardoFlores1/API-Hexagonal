package com.example.curso.application.services;

import com.example.curso.application.ports.input.CategoryServicePort;
import com.example.curso.application.ports.output.CategoryPersistencePort;
import com.example.curso.domain.exceptions.CategoryNotFoundException;
import com.example.curso.domain.models.Category;
import com.example.curso.domain.utils.CategoryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
    public Category create(Category category) {
        var dateTime = LocalDateTime.now();
        category.setStatus(CategoryStatus.ENABLED);
        category.setCreatedAt(dateTime);
        category.setUpdatedAt(dateTime);
        return categoryPersistencePort.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        return categoryPersistencePort.findById(id)
                .map(categoryFound -> {
                    categoryFound.setName(category.getName());
                    categoryFound.setStatus(category.getStatus());
                    categoryFound.setUpdatedAt(LocalDateTime.now());
                    return categoryPersistencePort.save(categoryFound);
                })
                .orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public void disabledById(Long categoryId) {
        Optional<Category> categoryOptional = categoryPersistencePort.findById(categoryId);
        if(categoryOptional.isPresent()) {
            categoryOptional.get().setUpdatedAt(LocalDateTime.now());
            categoryOptional.get().setStatus(CategoryStatus.DISABLED);
            categoryPersistencePort.save(categoryOptional.get());
        }
        throw new CategoryNotFoundException();
    }
}
