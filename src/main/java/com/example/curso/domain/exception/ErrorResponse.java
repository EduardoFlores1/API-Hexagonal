package com.example.curso.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private int statusCode;
    private String url;
    private String method;
    private String code;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;
}
