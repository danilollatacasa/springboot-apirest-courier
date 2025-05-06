package com.example.senati.service.impl;

import com.example.senati.model.repository.DistritoRepository;
import com.example.senati.model.entity.Distrito;
import com.example.senati.service.IDistrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistritoImpl implements IDistrito {

    @Autowired
    private DistritoRepository distritoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Distrito> getAll() {
        return distritoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Distrito findById(Integer id) {
        return distritoRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Distrito save(Distrito distrito) {
        return distritoRepository.save(distrito);
    }

    @Transactional
    @Override
    public void delete(Distrito distrito) {
        distritoRepository.delete(distrito);
    }
}
