package com.example.curso.application.services;

import com.example.curso.application.dto.categoy.CategoryRequest;
import com.example.curso.application.dto.categoy.CategoryResponse;
import com.example.curso.application.mapper.CategoryServiceMapper;
import com.example.curso.application.ports.input.CategoryServicePort;
import com.example.curso.application.ports.output.CategoryPersistencePort;
import com.example.curso.domain.enums.StatusEnum;
import com.example.curso.domain.exception.NotFoundException;
import com.example.curso.domain.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final CategoryPersistencePort categoryPersistencePort;
    private final CategoryServiceMapper categoryServiceMapper;

    @Override
    public CategoryResponse findById(Long id) {
        return categoryPersistencePort.findById(id)
                .map(categoryServiceMapper::toResponse)
                .orElseThrow(NotFoundException::category);
    }

    @Override
    public Page<CategoryResponse> findAll(Pageable pageable) {
        return categoryPersistencePort.findAll(pageable)
                .map(categoryServiceMapper::toResponse);
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = categoryServiceMapper.toModel(request);
        category.setStatus(StatusEnum.ENABLED);
        return categoryServiceMapper.toResponse(
                categoryPersistencePort.save(category)
        );
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        return categoryPersistencePort.findById(id)
                .map(categoryFound -> {
                    categoryFound.setName(request.getName());
                    return categoryPersistencePort.save(categoryFound);
                })
                .map(categoryServiceMapper::toResponse)
                .orElseThrow(NotFoundException::category);
    }

    @Override
    public void disabledById(Long categoryId) {
        Optional<Category> categoryOptional = categoryPersistencePort.findById(categoryId);
        if(categoryOptional.isPresent()) {
            if (categoryOptional.get().getStatus().equals(StatusEnum.DISABLED)) {
                throw new RuntimeException("Estado de la categoria desabilitado!");
            }
            categoryOptional.get().setStatus(StatusEnum.DISABLED);
            categoryPersistencePort.save(categoryOptional.get());
            return;
        }
        throw NotFoundException.category();
    }
}
