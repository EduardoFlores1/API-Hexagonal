package com.example.curso.application.services;

import com.example.curso.application.ports.input.ProductServicePort;
import com.example.curso.application.ports.output.ProductPersistencePort;
import com.example.curso.domain.exceptions.ProductNotFoundException;
import com.example.curso.domain.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return productPersistencePort.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        return productPersistencePort.findById(id)
                .map(productFound -> {
                    productFound.setName(product.getName());
                    productFound.setPrice(product.getPrice());
                    productFound.setStatus(product.getStatus());
                    return productPersistencePort.save(productFound);
                })
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if(productPersistencePort.findById(id).isPresent()) {
            productPersistencePort.deleteById(id);
        }
        throw new ProductNotFoundException();
    }
}
