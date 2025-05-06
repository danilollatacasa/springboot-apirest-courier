package com.example.senati.controller;

import com.example.senati.model.entity.EstadoPaquete;
import com.example.senati.service.IEstadoPaquete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EstadoPaqueteController {

    @Autowired
    private IEstadoPaquete estadoPaqueteService;

    @GetMapping("/estadopaquetes")
    public List<EstadoPaquete> getAll(){
        return estadoPaqueteService.getAll();
    }

    @GetMapping("/estadopaquetes/{id}")
    public EstadoPaquete getById(@PathVariable Integer id) {
        return estadoPaqueteService.findById(id);
    }

    @PostMapping("/estadopaquetes")
    public EstadoPaquete create(@RequestBody EstadoPaquete estadoPaquete){
        return estadoPaqueteService.save(estadoPaquete);
    }

    @PutMapping("/estadopaquetes/{id}")
    public EstadoPaquete save(@PathVariable Integer id, @RequestBody EstadoPaquete estadoPaquete){
        estadoPaquete.setId(id);
        return estadoPaqueteService.save(estadoPaquete);
    }

    @DeleteMapping("estadopaquetes/{id}")
    public void delete(@PathVariable Integer id){
        EstadoPaquete estadopaqueteDelete = estadoPaqueteService.findById(id);
        estadoPaqueteService.delete(estadopaqueteDelete);
    }

}
