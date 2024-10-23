package com.example.curso.infraestructure.adapters.output.persistence.repository;

import com.example.curso.infraestructure.adapters.output.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
