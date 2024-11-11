package com.example.curso.application.mapper;

import com.example.curso.application.dto.product.ProductRequest;
import com.example.curso.application.dto.product.ProductResponse;
import com.example.curso.domain.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductServiceMapper {
    @Mapping(target = "category.id", source = "categoryId")
    Product toModel(ProductRequest request);
    @Mapping(target = "categoryName", source = "category.name")
    ProductResponse toResponse(Product product);
}
