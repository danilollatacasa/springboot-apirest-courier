package com.example.senati.controller;

import com.example.senati.model.entity.Rol;
import com.example.senati.service.IRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class RolController {

    @Autowired
    private IRol rolService;

    @GetMapping("/roles")
    public List<Rol> getAll(){
        return rolService.getAll();
    }

    @GetMapping("/roles/{id}")
    public Rol getById(@PathVariable Integer id){
        return rolService.getById(id);
    }

    @PostMapping("/roles")
    public Rol create(@RequestBody Rol rol){
        return rolService.save(rol);
    }

    @PutMapping("roles/{id}")
    public Rol update(@PathVariable Integer id, @RequestBody Rol rol) {
        rol.setId(id);
        return rolService.save(rol);
    }

    @DeleteMapping("roles/{id}")
    public void delete(@PathVariable Integer id){
        Rol rolDelete = rolService.getById(id);
        rolService.delete(rolDelete);
    }
}
