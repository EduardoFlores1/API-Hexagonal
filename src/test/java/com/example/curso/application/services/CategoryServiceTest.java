package com.example.curso.application.services;

import com.example.curso.CategoryDataProvider;
import com.example.curso.application.dto.categoy.CategoryRequest;
import com.example.curso.application.dto.categoy.CategoryResponse;
import com.example.curso.application.mapper.CategoryServiceMapper;
import com.example.curso.application.ports.output.CategoryPersistencePort;
import com.example.curso.domain.enums.StatusEnum;
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
        Optional<Category> modelMock = CategoryDataProvider.modelOptionalMock();
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
        CategoryRequest modelRequestMock = CategoryDataProvider.modelRequestMock();
        Category modelMappedMock = new Category(null, modelRequestMock.getName(), modelRequestMock.getStatus(), null, null);
        Category modelCreatedMock = CategoryDataProvider.modelMock();
        CategoryResponse modelResponseMock = CategoryDataProvider.modelResponseMock();

        // When ok siu
        when(categoryServiceMapper.toModel(any(CategoryRequest.class))).thenReturn(modelMappedMock);
        when(categoryPersistencePort.save(any(Category.class))).thenReturn(modelCreatedMock);
        when(categoryServiceMapper.toResponse(any(Category.class))).thenReturn(modelResponseMock);
        CategoryResponse result = categoryService.create(modelRequestMock);

        // Then
        assertEquals(modelRequestMock.getName(), result.getName());
        assertEquals(modelRequestMock.getStatus(), result.getStatus());
        assertEquals(StatusEnum.ENABLED, result.getStatus());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());

        verify(categoryServiceMapper).toModel(any(CategoryRequest.class));
        verify(categoryPersistencePort).save(any(Category.class));
        verify(categoryServiceMapper).toResponse(any(Category.class));
    }

    @Test
    public void updateTest() {

        // Given
        Long idModel = 1L;
        CategoryRequest modelRequestMock = CategoryDataProvider.modelRequestMock();
        Optional<Category> modelOptionalMock = CategoryDataProvider.modelOptionalMock();
        Category modelUpdatedMock = CategoryDataProvider.modelMock();
        CategoryResponse modelResponseMock = CategoryDataProvider.modelResponseMock();

        // When ok siu
        when(categoryPersistencePort.findById(anyLong())).thenReturn(modelOptionalMock);
        when(categoryPersistencePort.save(any(Category.class))).thenReturn(modelUpdatedMock);
        when(categoryServiceMapper.toResponse(any(Category.class))).thenReturn(modelResponseMock);
        CategoryResponse result = categoryService.update(idModel, modelRequestMock);

        // Then
        assertEquals(modelRequestMock.getName(), result.getName());
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());

        verify(categoryPersistencePort).findById(anyLong());
        verify(categoryPersistencePort).save(any(Category.class));
        verify(categoryServiceMapper).toResponse(any(Category.class));
    }

    @Test
    public void updateNotFoundExceptionTest() {
        // Given

        // When
        when(categoryPersistencePort.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class,
                () -> categoryService.update(anyLong(), CategoryDataProvider.modelRequestMock())
                );

        verify(categoryPersistencePort).findById(anyLong());
        verify(categoryPersistencePort, never()).save(any(Category.class));
    }

    @Test
    public void disabledByIdTest() {

        // Given
        Optional<Category> modelMock = CategoryDataProvider.modelOptionalMock();

        // When
        when(categoryPersistencePort.findById(anyLong())).thenReturn(modelMock);
        categoryService.disabledById(anyLong());

        // Then
        verify(categoryPersistencePort).findById(anyLong());
    }

    @Test
    public void disabledByIdNotFoundExceptionTest() {

        // Given

        // When
        when(categoryPersistencePort.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class,
                () -> categoryService.disabledById(anyLong())
        );

        verify(categoryPersistencePort).findById(anyLong());
        verify(categoryPersistencePort, never()).save(any(Category.class));
    }

    @Test
    public void disabledByIdBadRequestExceptionTest() {

        // Given
        Optional<Category> modelDisabledMock = CategoryDataProvider.modelDisabledOptionalMock();

        // When
        when(categoryPersistencePort.findById(anyLong())).thenReturn(modelDisabledMock);

        // Then
        assertThrows(RuntimeException.class,
                () -> categoryService.disabledById(anyLong())
        );

        verify(categoryPersistencePort).findById(anyLong());
        verify(categoryPersistencePort, never()).save(any(Category.class));
    }
}