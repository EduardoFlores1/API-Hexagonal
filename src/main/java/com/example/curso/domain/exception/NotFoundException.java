package com.example.curso.domain.exception;

import static com.example.curso.util.ErrorCatalog.PRODUCT_NOT_FOUND;
import static com.example.curso.util.ErrorCatalog.CATEGORY_NOT_FOUND;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private String code;
    private String message;

    private NotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static NotFoundException category() {
        return new NotFoundException(CATEGORY_NOT_FOUND.getCode(), CATEGORY_NOT_FOUND.getMessage());
    }
    public static NotFoundException product() {
        return new NotFoundException(PRODUCT_NOT_FOUND.getCode(), PRODUCT_NOT_FOUND.getMessage());
    }
}

