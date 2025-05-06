package com.example.senati.service;

import com.example.senati.model.entity.Jornada;

import java.util.List;

public interface IJornada {

    List<Jornada> listarJornadas();
    Jornada getJornadaById(Integer id);
    Jornada iniciarJornada(Jornada jornada);
    Jornada finalizarJornada(Integer id);
    void eliminarJornada(Jornada jornada);
}
