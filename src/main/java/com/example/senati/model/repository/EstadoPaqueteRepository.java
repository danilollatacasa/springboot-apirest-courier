package com.example.senati.model.repository;

import com.example.senati.model.entity.EstadoPaquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstadoPaqueteRepository extends JpaRepository<EstadoPaquete,Integer> {
    @Query("SELECT e FROM EstadoPaquete e WHERE LOWER(e.descripcion) = LOWER(:descripcion)")
    Optional<EstadoPaquete> findByDescripcionIgnoreCase(@Param("descripcion") String descripcion);
}
