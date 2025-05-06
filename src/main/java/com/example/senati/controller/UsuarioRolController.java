package com.example.senati.controller;

import com.example.senati.model.entity.UsuarioRol;
import com.example.senati.service.IUsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioRolController {

    @Autowired
    private IUsuarioRol usuarioRolService;

    @GetMapping("/usuarioroles")
    public List<UsuarioRol> obtenerTodosRolesPorUsuario() {
        return usuarioRolService.obtenerTodosRolesPorUsuario();
    }

    @GetMapping("/usuarioroles/{usuarioId}")
    public List<UsuarioRol> obtenerRolesPorUsuario(@PathVariable Integer usuarioId) {
        return usuarioRolService.obtenerRolesPorUsuario(usuarioId);
    }

    @PostMapping("/usuarioroles")
    public void asginarRol(@RequestBody UsuarioRol usuarioRol) {
        usuarioRolService.asignarRolAUsuario(usuarioRol);
    }

    @DeleteMapping("/usuarioroles/{usuarioId}/{rolId}")
    public void eliminarRol(@PathVariable Integer usuarioId, @PathVariable Integer rolId) {
        usuarioRolService.eliminarRolDeUsuario(usuarioId, rolId);
    }
}
