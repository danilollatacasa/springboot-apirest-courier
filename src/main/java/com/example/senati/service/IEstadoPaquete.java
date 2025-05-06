package com.example.senati.service;

import com.example.senati.model.entity.EstadoPaquete;

import java.util.List;
import java.util.Optional;

public interface IEstadoPaquete {


    List<EstadoPaquete> getAll();
    EstadoPaquete findById(Integer id);
    EstadoPaquete save(EstadoPaquete estadoPaquete);
    void delete(EstadoPaquete estadoPaquete);
    Optional<EstadoPaquete> findByDescripcion(String descripcion);
}
