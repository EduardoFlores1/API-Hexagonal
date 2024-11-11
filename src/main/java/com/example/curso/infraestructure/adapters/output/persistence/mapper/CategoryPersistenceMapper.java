package com.example.curso.infraestructure.adapters.output.persistence.mapper;

import com.example.curso.domain.models.Category;
import com.example.curso.infraestructure.adapters.output.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper {
    CategoryEntity toEntity(Category category);
    Category toModel(CategoryEntity entity);
}
