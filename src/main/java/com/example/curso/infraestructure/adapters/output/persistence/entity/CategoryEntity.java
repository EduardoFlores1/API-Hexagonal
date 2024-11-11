package com.example.curso.infraestructure.adapters.output.persistence.entity;

import com.example.curso.domain.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        var dateTime = LocalDateTime.now();
        this.createdAt = dateTime;
        this.updatedAt = dateTime;
    }

    @PreUpdate
    private void postPersist() {
        this.updatedAt = LocalDateTime.now();
    }
}
