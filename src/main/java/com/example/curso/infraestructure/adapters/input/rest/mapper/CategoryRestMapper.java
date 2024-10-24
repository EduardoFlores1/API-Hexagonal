package com.example.curso.infraestructure.adapters.input.rest.mapper;

import com.example.curso.domain.models.Category;
import com.example.curso.infraestructure.adapters.input.rest.model.category.request.CategoryCreate;
import com.example.curso.infraestructure.adapters.input.rest.model.category.response.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryRestMapper {
    CategoryResponse toResponse(Category category);
    Category toCategory(CategoryCreate create);
}
