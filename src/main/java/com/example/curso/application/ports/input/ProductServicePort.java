package com.example.curso.application.ports.input;

import com.example.curso.domain.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServicePort {
    Product findById(Long id);
    Page<Product> findAll(Pageable pageable);
    Product create(Product product);
    Product update(Long id, Product product);
    void deleteById(Long id);
}
