package com.example.senati.service;

import com.example.senati.model.entity.Distrito;

import java.util.List;

public interface IDistrito {

    List<Distrito> getAll();
    Distrito findById(Integer id);
    Distrito save(Distrito distrito);
    void delete(Distrito distrito);


}
