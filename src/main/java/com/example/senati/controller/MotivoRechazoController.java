package com.example.senati.controller;

import com.example.senati.model.entity.MotivoRechazo;
import com.example.senati.service.IMotivoRechazo;
import com.example.senati.service.impl.MotivoRechazoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class MotivoRechazoController {

    @Autowired
    private IMotivoRechazo motivoRechazoService;


    @GetMapping("/motivorechazos")
    public List<MotivoRechazo> getAll(){
        return motivoRechazoService.getAll();
    }

    @GetMapping("/motivorechazos/{id}")
    public MotivoRechazo getById(@PathVariable Integer id) {
        return motivoRechazoService.findById(id);
    }

    @PostMapping("/motivorechazos")
    public MotivoRechazo create(@RequestBody MotivoRechazo motivoRechazo){
        return motivoRechazoService.save(motivoRechazo);
    }

    @PutMapping("/motivorechazo/{id}")
    public MotivoRechazo update(@PathVariable Integer id, @RequestBody MotivoRechazo motivoRechazo){
        motivoRechazo.setId(id);
        return motivoRechazoService.save(motivoRechazo);
    }

    @DeleteMapping("/motivorechazos/{id}")
    public void delete(@PathVariable Integer id){
        MotivoRechazo motivoRechazoDelete = motivoRechazoService.findById(id);
        motivoRechazoService.delete(motivoRechazoDelete);
    }









}
