package com.example.curso;

import com.example.curso.application.dto.categoy.CategoryRequest;
import com.example.curso.application.dto.categoy.CategoryResponse;
import com.example.curso.domain.enums.StatusEnum;
import com.example.curso.domain.models.Category;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public class CategoryDataProvider {

    public static Optional<Category> modelMock() {
        var createDateTime = LocalDateTime.of(2024, Month.DECEMBER, 1,12,12);
        var updateDateTime = LocalDateTime.of(2024, Month.DECEMBER, 3,15,15);

        return Optional.of(
                new Category(1L, "Micrófonos", StatusEnum.DISABLED, createDateTime, updateDateTime)
        );
    }

    public static CategoryResponse modelResponseMock() {
        var createDateTime = LocalDateTime.of(2024, Month.DECEMBER, 1,12,12);
        var updateDateTime = LocalDateTime.of(2024, Month.DECEMBER, 3,15,15);

        return new CategoryResponse(1L, "Micrófonos", StatusEnum.DISABLED, createDateTime, updateDateTime);
    }

    public static List<Category> modelListMock() {
        var createDateTime = LocalDateTime.of(2024, Month.DECEMBER, 1,12,12);
        var updateDateTime = LocalDateTime.of(2024, Month.DECEMBER, 3,15,15);

        return List.of(
                new Category(1L, "Micrófonos", StatusEnum.DISABLED, createDateTime, updateDateTime),
                new Category(2L, "Sillas Gamer", StatusEnum.ENABLED, createDateTime, updateDateTime),
                new Category(3L, "Monitores", StatusEnum.ENABLED, createDateTime, updateDateTime),
                new Category(4L, "Teclados", StatusEnum.DISABLED, createDateTime, updateDateTime),
                new Category(5L, "Mouses", StatusEnum.ENABLED, createDateTime, updateDateTime)
        );
    }

    public static List<CategoryResponse> modelResponseListMock() {
        var createDateTime = LocalDateTime.of(2024, Month.DECEMBER, 1,12,12);
        var updateDateTime = LocalDateTime.of(2024, Month.DECEMBER, 3,15,15);

        return List.of(
                new CategoryResponse(1L, "Micrófonos", StatusEnum.DISABLED, createDateTime, updateDateTime),
                new CategoryResponse(2L, "Sillas Gamer", StatusEnum.ENABLED, createDateTime, updateDateTime),
                new CategoryResponse(3L, "Monitores", StatusEnum.ENABLED, createDateTime, updateDateTime),
                new CategoryResponse(4L, "Teclados", StatusEnum.DISABLED, createDateTime, updateDateTime),
                new CategoryResponse(5L, "Mouses", StatusEnum.ENABLED, createDateTime, updateDateTime)
        );
    }

    public static CategoryRequest newModelMock() {
        CategoryRequest request = new CategoryRequest();
        setField(request, "name", "Gráficas");
        setField(request, "status", StatusEnum.ENABLED);

        return request;
    }

    private static void setField(Object target, String fieldName, Object fieldValue) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, fieldValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error al establecer el valor del campo", e);
        }
    }
}
