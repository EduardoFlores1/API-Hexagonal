package com.example.curso.application.services;

import com.example.curso.application.ports.input.ProductServicePort;
import com.example.curso.application.ports.output.ProductPersistencePort;
import com.example.curso.domain.exceptions.ProductNotFoundException;
import com.example.curso.domain.models.Product;
import com.example.curso.domain.utils.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;

    @Override
    public Product findById(Long id) {
        return productPersistencePort.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productPersistencePort.findAll(pageable);
    }

    @Override
    public Product create(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productPersistencePort.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        return productPersistencePort.findById(id)
                .map(productFound -> {
                    productFound.setName(product.getName());
                    productFound.setPrice(product.getPrice());
                    productFound.setStatus(product.getStatus());
                    productFound.setUpdatedAt(LocalDateTime.now());
                    return productPersistencePort.save(productFound);
                })
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void disabledById(Long id) {
        Optional<Product> productOptional = productPersistencePort.findById(id);
        if(productOptional.isPresent()) {
            productOptional.get().setStatus(ProductStatus.DISABLED);
            productPersistencePort.save(productOptional.get());
        }
        throw new ProductNotFoundException();
    }
}
