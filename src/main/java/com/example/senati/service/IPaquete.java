package com.example.senati.service;

import com.example.senati.model.entity.Paquete;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPaquete {

    List<Paquete> getAll();
    Paquete getById(Integer id);
    Paquete save(Paquete paquete);
    Paquete update(Integer id, Paquete paqueteActualizado);
    void delete(Paquete paquete);
}
