package com.example.curso.application.ports.output;

import com.example.curso.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductPersistencePort {
    Optional<Product> findById(Long id);
    Page<Product> findAll(Pageable pageable);
    Product save(Product product);
    void deleteById(Long id);
}
