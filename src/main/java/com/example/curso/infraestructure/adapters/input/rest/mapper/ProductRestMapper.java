package com.example.curso.infraestructure.adapters.input.rest.mapper;

import com.example.curso.domain.models.Product;
import com.example.curso.infraestructure.adapters.input.rest.model.request.ProductCreate;
import com.example.curso.infraestructure.adapters.input.rest.model.response.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {

    ProductResponse toProductResponse(Product product);
    Product toProduct(ProductCreate create);
}
