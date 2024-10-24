package com.example.curso.infraestructure.adapters.input.rest;

import com.example.curso.application.ports.input.ProductServicePort;
import com.example.curso.infraestructure.adapters.input.rest.mapper.ProductRestMapper;
import com.example.curso.infraestructure.adapters.input.rest.model.product.request.ProductCreate;
import com.example.curso.infraestructure.adapters.input.rest.model.product.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestAdapter {

    private final ProductServicePort productServicePort;
    private final ProductRestMapper productRestMapper;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(Pageable pageable) {
        Page<ProductResponse> productPage = productServicePort.findAll(pageable)
                .map(productRestMapper::toResponse);

        if (productPage.hasContent()) {
            return ResponseEntity.ok(productPage);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long productId) {
        return ResponseEntity.ok(
                productRestMapper.toResponse(productServicePort.findById(productId))
        );
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createOne(@Valid @RequestBody ProductCreate create) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productRestMapper.toResponse(
                        productServicePort.create(productRestMapper.toProduct(create))
                ));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductCreate create, @PathVariable Long productId) {
        return ResponseEntity
                .ok(productRestMapper.toResponse(
                        productServicePort.update(productId, productRestMapper.toProduct(create))));
    }

    @PatchMapping("/{productId}/disabled")
    public ResponseEntity<Void> disabledById(@PathVariable Long productId) {
        productServicePort.disabledById(productId);
        return ResponseEntity.noContent().build();
    }
}
