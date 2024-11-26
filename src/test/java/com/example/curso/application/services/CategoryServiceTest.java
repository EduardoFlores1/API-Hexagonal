package com.example.curso.application.services;

import com.example.curso.CategoryDataProvider;
import com.example.curso.application.dto.categoy.CategoryRequest;
import com.example.curso.application.dto.categoy.CategoryResponse;
import com.example.curso.application.mapper.CategoryServiceMapper;
import com.example.curso.application.ports.output.CategoryPersistencePort;
import com.example.curso.domain.exception.NotFoundException;
import com.example.curso.domain.models.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @Mock
    private CategoryServiceMapper categoryServiceMapper;

    @Test
    public void findByIdTest() {

        // Given
        Long idCategory = 1L;
        Optional<Category> modelMock = CategoryDataProvider.modelMock();
        CategoryResponse modelResponseMock = CategoryDataProvider.modelResponseMock();

        // When
        when(categoryPersistencePort.findById(anyLong())).thenReturn(modelMock);
        when(categoryServiceMapper.toResponse(any(Category.class))).thenReturn(modelResponseMock);
        CategoryResponse result = categoryService.findById(idCategory);

        // Then
        assertNotNull(result);
        assertEquals(modelMock.get().getId(), result.getId());
        assertEquals(modelMock.get().getName(), result.getName());
        assertEquals(modelMock.get().getStatus(), result.getStatus());
        assertEquals(modelMock.get().getCreatedAt(), result.getCreatedAt());
        assertEquals(modelMock.get().getUpdatedAt(), result.getUpdatedAt());

        verify(categoryPersistencePort).findById(anyLong());
        verify(categoryServiceMapper).toResponse(any(Category.class));
    }

    @Test
    public void findByIdNotFoundExceptionTest() {
        // Given

        // When
        when(categoryPersistencePort.findById(anyLong()))
                .thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class,
                () -> categoryService.findById(anyLong())
        );

        verify(categoryPersistencePort).findById(anyLong());
    }

    @Test
    public void findAllTest() {

        // Given
        Pageable pageable = PageRequest.of(0, 5);
        List<Category> modelListMock = CategoryDataProvider.modelListMock();
        int sizeList = modelListMock.size();
        List<CategoryResponse> modelResponseListMock = CategoryDataProvider.modelResponseListMock();
        Page<Category> categoryPageMock = new PageImpl<>(modelListMock, pageable, modelListMock.size());

        // When
        when(categoryPersistencePort.findAll(any(Pageable.class))).thenReturn(categoryPageMock);
        for (int i = 0; i < sizeList; i++) {
            when(categoryServiceMapper.toResponse(modelListMock.get(i)))
                    .thenReturn(modelResponseListMock.get(i));
        }
        Page<CategoryResponse> result = categoryService.findAll(pageable);

        // Then
        assertEquals(modelListMock.size(), result.getTotalElements());

        for(int i = 0;  i < sizeList; i++) {
            assertEquals(modelListMock.get(i).getId(), result.getContent().get(i).getId());
            assertEquals(modelListMock.get(i).getName(), result.getContent().get(i).getName());
            assertEquals(modelListMock.get(i).getStatus(), result.getContent().get(i).getStatus());
        }

        verify(categoryPersistencePort).findAll(any(Pageable.class));
        verify(categoryServiceMapper, times(modelListMock.size())).toResponse(any(Category.class));
    }

    @Test
    public void createTest() {

        // Given
        CategoryRequest modelRequestMock = CategoryDataProvider.newModelMock();
        System.out.println(modelRequestMock.getName());

        // When ok siu

        // Then
    }
}