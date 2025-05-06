package com.example.senati.service;

import com.example.senati.model.entity.Rol;

import java.util.List;

public interface IRol {
    List<Rol> getAll();
    Rol getById(Integer id);
    Rol save(Rol rol);
    void delete(Rol rol);
}
