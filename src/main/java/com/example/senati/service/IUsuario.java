package com.example.senati.service;

import com.example.senati.model.entity.Usuario;

import java.util.List;

public interface IUsuario {
    List<Usuario> getAll();
    Usuario getById(Integer id);
    Usuario save(Usuario usuario);
    void delete(Usuario usuario);
}
