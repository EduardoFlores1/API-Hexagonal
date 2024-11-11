package com.example.curso.util;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred"),
    GENERIC_INVALID_ARGS("ERR_GEN_002", "Invalid arguments in "),

    CATEGORY_NOT_FOUND("ERR_CATEGORY_001", "Category not found"),
    CATEGORY_INVALID("ERR_CATEGORY_002", "Invalid category parameters"),

    PRODUCT_NOT_FOUND("ERR_PRODUCT_001", "Product not found"),
    PRODUCT_INVALID("ERR_PRODUCT_002", "Invalid product parameters");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
