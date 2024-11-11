package com.example.curso.application.dto.categoy;

import com.example.curso.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private StatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
