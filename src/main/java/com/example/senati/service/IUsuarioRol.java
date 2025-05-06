package com.example.senati.service;

import com.example.senati.model.entity.UsuarioRol;
import com.example.senati.model.entity.UsuarioRolPK;

import java.util.List;

public interface IUsuarioRol {
    List<UsuarioRol>obtenerTodosRolesPorUsuario();
    List<UsuarioRol> obtenerRolesPorUsuario(Integer usuarioId);
    void asignarRolAUsuario(UsuarioRol usuarioRol);
    void eliminarRolDeUsuario(Integer usuarioId, Integer rolId);
}
