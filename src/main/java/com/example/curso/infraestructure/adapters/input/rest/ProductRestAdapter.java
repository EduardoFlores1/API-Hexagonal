package com.example.curso.infraestructure.adapters.input.rest;

import com.example.curso.application.dto.product.ProductRequest;
import com.example.curso.application.dto.product.ProductResponse;
import com.example.curso.application.ports.input.ProductServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestAdapter {

    private final ProductServicePort productServicePort;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponse> productPage = productServicePort.findAll(pageable);

        if (productPage.hasContent()) {
            return ResponseEntity.ok(productPage);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long productId) {
        return ResponseEntity.ok(productServicePort.findById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createOne(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productServicePort.create(request));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductRequest request, @PathVariable Long productId) {
        return ResponseEntity
                .ok(productServicePort.update(productId, request));
    }

    @PatchMapping("/{productId}/disabled")
    public ResponseEntity<Void> disabledById(@PathVariable Long productId) {
        productServicePort.disabledById(productId);
        return ResponseEntity.noContent().build();
    }
}
