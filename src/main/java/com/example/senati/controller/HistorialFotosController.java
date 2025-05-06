package com.example.senati.controller;
import com.example.senati.model.entity.HistorialFotos;
import com.example.senati.service.IHistorialFotos;
import com.example.senati.service.IStorageAzure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("api/v1")
public class HistorialFotosController {

    @Autowired
    private IStorageAzure storageAzureService;


    @Autowired
    private IHistorialFotos historialFotosService;

    @GetMapping("/historialfotos")
    public List<HistorialFotos> getAll() {
        return historialFotosService.getAll();
    }

    @GetMapping("/historialfotos/{id}")
    public HistorialFotos findById(@PathVariable Integer id){
        return historialFotosService.findById(id);
    }

    @PostMapping(value = "/historialfotos", consumes = "multipart/form-data")
    public HistorialFotos uploadFoto(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("El archivo está vacío");
        }
        return historialFotosService.save(file);
    }



    @DeleteMapping("/historialfotos/{id}")
    public void delete(@PathVariable Integer id){
        HistorialFotos historialFotosDelete = historialFotosService.findById(id);
        historialFotosService.delete(historialFotosDelete);
    }
}
