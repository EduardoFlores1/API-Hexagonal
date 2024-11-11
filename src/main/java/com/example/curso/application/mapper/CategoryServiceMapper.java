package com.example.curso.application.mapper;

import com.example.curso.application.dto.categoy.CategoryRequest;
import com.example.curso.application.dto.categoy.CategoryResponse;
import com.example.curso.domain.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryServiceMapper {
    @Mapping(target = "status", ignore = true)
    Category toModel(CategoryRequest request);
    CategoryResponse toResponse(Category category);
}
