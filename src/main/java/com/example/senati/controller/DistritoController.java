package com.example.senati.controller;

import com.example.senati.model.entity.Distrito;
import com.example.senati.service.IDistrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class DistritoController {

    @Autowired
    private IDistrito distritoService;


    @GetMapping("/distritos")
    public List<Distrito> getAll() {
        return distritoService.getAll();
    }

    @GetMapping("/distritos/{id}")
    public Distrito getById(@PathVariable Integer id){
        return distritoService.findById(id);
    }

    @PostMapping("/distritos")
    public Distrito create(@RequestBody Distrito distrito) {
        return distritoService.save(distrito);
    }

    @PutMapping("/distritos/{id}")
    public Distrito update(@PathVariable Integer id,@RequestBody Distrito distrito) {
        distrito.setId(id);
        return  distritoService.save(distrito);
    }

    @DeleteMapping("/distritos/{id}")
    public void delete(@PathVariable Integer id) {
        Distrito distritoDelete = distritoService.findById(id);
        distritoService.delete(distritoDelete);
    }



}

