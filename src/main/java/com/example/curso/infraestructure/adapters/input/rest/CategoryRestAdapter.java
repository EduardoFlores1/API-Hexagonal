package com.example.curso.infraestructure.adapters.input.rest;

import com.example.curso.application.dto.categoy.CategoryRequest;
import com.example.curso.application.dto.categoy.CategoryResponse;
import com.example.curso.application.ports.input.CategoryServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryRestAdapter {

    private final CategoryServicePort servicePort;

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<CategoryResponse> categoryPage = servicePort.findAll(pageable);

        if (categoryPage.hasContent()) {
            return ResponseEntity.ok(categoryPage);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(servicePort.findById(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createOne(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicePort.create(request));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryRequest request, @PathVariable Long categoryId) {
        return ResponseEntity
                .ok(servicePort.update(categoryId, request));
    }

    @PatchMapping("/{categoryId}/disabled")
    public ResponseEntity<Void> disabledById(@PathVariable Long categoryId) {
        servicePort.disabledById(categoryId);
        return ResponseEntity.noContent().build();
    }

}
