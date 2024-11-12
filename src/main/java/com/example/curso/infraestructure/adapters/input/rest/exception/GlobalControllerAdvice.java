package com.example.curso.infraestructure.adapters.input.rest.exception;

import com.example.curso.domain.exception.ErrorResponse;
import static com.example.curso.util.ErrorCatalog.GENERIC_ERROR;
import static com.example.curso.util.ErrorCatalog.GENERIC_INVALID_ARGS;

import com.example.curso.domain.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(
            HttpServletRequest request,
            Exception ex) {

        return ErrorResponse.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .code(GENERIC_ERROR.getCode())
                .message(GENERIC_ERROR.getMessage())
                .details(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ErrorResponse handleMethodArgumentNotValidException(
            HttpServletRequest request,
            MethodArgumentNotValidException ex
    ) {

        return ErrorResponse.builder()
                .statusCode(HttpStatus.PRECONDITION_FAILED.value())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .code(GENERIC_INVALID_ARGS.getCode())
                .message(GENERIC_INVALID_ARGS.getMessage().concat(ex.getObjectName()))
                .details(ex.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(
            HttpServletRequest request,
            NotFoundException ex
    ) {

        return ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .code(ex.getCode())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
