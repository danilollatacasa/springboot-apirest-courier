package com.example.senati.service;

import com.example.senati.model.entity.HistorialFotos;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHistorialFotos {

    List<HistorialFotos> getAll();
    HistorialFotos findById(Integer id);
    HistorialFotos save(MultipartFile file);
    void delete(HistorialFotos historialFotos);

}
