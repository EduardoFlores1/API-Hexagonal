package com.example.curso.infraestructure.adapters.output.persistence.mapper;

import com.example.curso.domain.models.Product;
import com.example.curso.infraestructure.adapters.output.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {
    ProductEntity toProductEntity(Product product);
    Product toProduct(ProductEntity productEntity);
}
