package com.example.senati.controller;

import com.example.senati.model.entity.Jornada;
import com.example.senati.service.IJornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1")
public class JornadaController {

    @Autowired
    private IJornada jornadaService;


    @GetMapping("/jornadas")
    public List<Jornada> getAll() {
        return jornadaService.listarJornadas();
    }

    @GetMapping("/jornadas/{id}")
    public Jornada getJornadaById(@PathVariable Integer id) {
        return  jornadaService.getJornadaById(id);
    }

    @PostMapping("/jornadas/iniciar")
    public Jornada iniciarJornada(@RequestBody Jornada jornada) {
        return jornadaService.iniciarJornada(jornada);
    }

    @PutMapping("/jornadas/finalizar/{id}")
    public Jornada finalizarJornada(@PathVariable Integer id){
        return jornadaService.finalizarJornada(id);
    }
    @DeleteMapping("/jornadas/{id}")
    public void eliminarJornada(@PathVariable Integer id) {
        Jornada jornadaDelete = jornadaService.getJornadaById(id);
        jornadaService.eliminarJornada(jornadaDelete);
    }
}
