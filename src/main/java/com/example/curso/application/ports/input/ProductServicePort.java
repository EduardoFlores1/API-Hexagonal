package com.example.curso.application.ports.input;

import com.example.curso.application.dto.product.ProductRequest;
import com.example.curso.application.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductServicePort {
    ProductResponse findById(Long id);
    Page<ProductResponse> findAll(Pageable pageable);
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    void disabledById(Long id);
}
