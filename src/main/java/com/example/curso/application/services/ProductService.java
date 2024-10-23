package com.example.curso.application.services;

import com.example.curso.application.ports.input.ProductServicePort;
import com.example.curso.application.ports.output.ProductPersistencePort;
import com.example.curso.domain.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductServicePort {

    private final ProductPersistencePort persistencePort;

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product update(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
