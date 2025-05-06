package com.example.senati.controller;


import com.example.senati.model.entity.Usuario;
import com.example.senati.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UsuarioController {

    @Autowired
    private IUsuario usuarioService;

    @GetMapping("/usuarios")
    public List<Usuario> getAll(){
        return usuarioService.getAll();
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getById(@PathVariable Integer id){
        return usuarioService.getById(id);
    }

    @PostMapping("/usuarios")
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public Usuario update(@PathVariable Integer id,@RequestBody Usuario usuario){
        usuario.setId(id);
        return usuarioService.save(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public void delete(@PathVariable Integer id) {
        Usuario usuarioDelete = usuarioService.getById(id);
        usuarioService.delete(usuarioDelete);
    }





}


