package com.example.curso.infraestructure.adapters.output.persistence;

import com.example.curso.application.ports.output.ProductPersistencePort;
import com.example.curso.domain.models.Product;
import com.example.curso.infraestructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import com.example.curso.infraestructure.adapters.output.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductRepository productRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id)
                .map(mapper::toProduct);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(mapper::toProduct);
    }

    @Override
    public Product save(Product product) {
        return mapper.toProduct(
                productRepository.save(mapper.toProductEntity(product))
        );
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
