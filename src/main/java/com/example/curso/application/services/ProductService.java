package com.example.curso.application.services;

import com.example.curso.application.dto.product.ProductRequest;
import com.example.curso.application.dto.product.ProductResponse;
import com.example.curso.application.mapper.ProductServiceMapper;
import com.example.curso.application.ports.input.ProductServicePort;
import com.example.curso.application.ports.output.CategoryPersistencePort;
import com.example.curso.application.ports.output.ProductPersistencePort;
import com.example.curso.domain.enums.StatusEnum;
import com.example.curso.domain.exception.NotFoundException;
import com.example.curso.domain.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final ProductServiceMapper serviceMapper;

    @Override
    public ProductResponse findById(Long id) {
        return productPersistencePort.findById(id)
                .map(serviceMapper::toResponse)
                .orElseThrow(NotFoundException::product);
    }

    @Override
    public Page<ProductResponse> findAll(Pageable pageable) {
        return productPersistencePort.findAll(pageable)
                .map(serviceMapper::toResponse);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        return categoryPersistencePort.findById(request.getCategoryId())
                        .map(category -> {
                            if(category.getStatus().equals(StatusEnum.DISABLED)) {
                                throw new RuntimeException("La categoria está desabilitada");
                            }

                            Product product = serviceMapper.toModel(request);
                            product.setCategory(category);
                            product.setStatus(StatusEnum.ENABLED);
                            return productPersistencePort.save(product);
                        })
                .map(serviceMapper::toResponse)
                .orElseThrow(NotFoundException::category);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        return productPersistencePort.findById(id)
                .map(productFound -> {
                    if(productFound.getStatus().equals(StatusEnum.DISABLED)) {
                        throw new RuntimeException("El producto está desabilitado");
                    }
                    Product product = serviceMapper.toModel(request);
                    product.setId(id);
                    // mantenmos estado, ya que el cambio se maneja en un servicio aparte
                    product.setStatus(productFound.getStatus());
                    return productPersistencePort.save(product);
                })
                .map(serviceMapper::toResponse)
                .orElseThrow(NotFoundException::product);
    }

    @Override
    public void disabledById(Long id) {
        Optional<Product> productOptional = productPersistencePort.findById(id);
        if(productOptional.isPresent()) {
            if (productOptional.get().getStatus().equals(StatusEnum.DISABLED)) {
                throw new RuntimeException("Estado del producto ya desabilitado!");
            }
            productOptional.get().setStatus(StatusEnum.DISABLED);
            productPersistencePort.save(productOptional.get());
        }
        throw NotFoundException.product();
    }

}
