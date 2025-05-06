package com.example.senati.controller;

import com.example.senati.model.entity.Paquete;
import com.example.senati.service.IPaquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PaqueteController {

    @Autowired
    private IPaquete paqueteService;

    @GetMapping("/paquetes")
    public List<Paquete> getAll() {
        return paqueteService.getAll();
    }

    @GetMapping("/paquetes/{id}")
    public Paquete getById(@PathVariable Integer id) {
        return paqueteService.getById(id);
    }

    @PostMapping("/paquetes")
    public Paquete create(@RequestBody Paquete paquete) {
        return paqueteService.save(paquete);
    }

    @PutMapping("/paquetes/{id}")
    public Paquete update(@PathVariable Integer id, @RequestBody Paquete paquete) {
        return paqueteService.update(id, paquete);
    }


    @DeleteMapping("/paquetes/{id}")
    public void delete(@PathVariable Integer id) {
        Paquete paqueteDelete = paqueteService.getById(id);
        paqueteService.delete(paqueteDelete);
    }
}
