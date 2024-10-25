package com.example.curso.infraestructure.adapters.input.rest;

import com.example.curso.application.ports.input.CategoryServicePort;
import com.example.curso.infraestructure.adapters.input.rest.mapper.CategoryRestMapper;
import com.example.curso.infraestructure.adapters.input.rest.model.category.request.CategoryCreate;
import com.example.curso.infraestructure.adapters.input.rest.model.category.response.CategoryResponse;
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

    private final CategoryServicePort categoryServicePort;
    private final CategoryRestMapper categoryRestMapper;

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<CategoryResponse> categoryPage = categoryServicePort.findAll(pageable)
                .map(categoryRestMapper::toResponse);

        if (categoryPage.hasContent()) {
            return ResponseEntity.ok(categoryPage);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(
                categoryRestMapper.toResponse(categoryServicePort.findById(categoryId))
        );
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createOne(@Valid @RequestBody CategoryCreate create) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryRestMapper.toResponse(
                        categoryServicePort.create(categoryRestMapper.toCategory(create))
                ));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryCreate create, @PathVariable Long categoryId) {
        return ResponseEntity
                .ok(categoryRestMapper.toResponse(
                        categoryServicePort.update(categoryId, categoryRestMapper.toCategory(create))));
    }

    @PatchMapping("/{categoryId}/disabled")
    public ResponseEntity<Void> disabledById(@PathVariable Long categoryId) {
        categoryServicePort.disabledById(categoryId);
        return ResponseEntity.noContent().build();
    }

}
