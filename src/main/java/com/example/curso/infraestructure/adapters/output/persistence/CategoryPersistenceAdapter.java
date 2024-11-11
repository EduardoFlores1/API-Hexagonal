package com.example.curso.infraestructure.adapters.output.persistence;

import com.example.curso.application.ports.output.CategoryPersistencePort;
import com.example.curso.domain.models.Category;
import com.example.curso.infraestructure.adapters.output.persistence.mapper.CategoryPersistenceMapper;
import com.example.curso.infraestructure.adapters.output.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPersistencePort {

    private final CategoryRepository categoryRepository;
    private final CategoryPersistenceMapper mapper;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id)
                .map(mapper::toModel);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(mapper::toModel);
    }

    @Override
    public Category save(Category category) {
        return mapper.toModel(
                categoryRepository.save(mapper.toEntity(category))
        );
    }
}
