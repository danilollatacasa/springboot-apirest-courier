package com.example.senati.service.impl;

import com.example.senati.model.repository.EstadoPaqueteRepository;
import com.example.senati.model.entity.EstadoPaquete;
import com.example.senati.service.IEstadoPaquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoPaqueteImpl implements IEstadoPaquete {

    @Autowired
    private EstadoPaqueteRepository estadoPaqueteRepository;

    @Transactional(readOnly = true)
    @Override
    public List<EstadoPaquete> getAll() {
        return estadoPaqueteRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public EstadoPaquete findById(Integer id){
        return estadoPaqueteRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public EstadoPaquete save(EstadoPaquete estadoPaquete){
        return estadoPaqueteRepository.save(estadoPaquete);
    }

    @Transactional
    @Override
    public void delete(EstadoPaquete estadoPaquete){
        estadoPaqueteRepository.delete(estadoPaquete);
    }

    @Override
    public Optional<EstadoPaquete> findByDescripcion(String descripcion) {
        return estadoPaqueteRepository.findByDescripcionIgnoreCase(descripcion);
    }
}
