package com.example.curso.infraestructure.adapters.input.rest.mapper;

import com.example.curso.domain.models.Product;
import com.example.curso.infraestructure.adapters.input.rest.model.product.request.ProductCreate;
import com.example.curso.infraestructure.adapters.input.rest.model.product.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryRestMapper.class)
public interface ProductRestMapper {
    ProductResponse toResponse(Product product);
    @Mapping(target = "Category.id", source = "categoryId")
    Product toProduct(ProductCreate create);
}
