package com.example.senati.service.impl;

import com.example.senati.model.entity.Usuario;
import com.example.senati.model.repository.UsuarioRepository;
import com.example.senati.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioImpl implements IUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario getById(Integer id){
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Usuario save(Usuario usuario){
        String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(contrasenaEncriptada);
        return usuarioRepository.save(usuario);

    }


    @Transactional
    @Override
    public void delete(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

}
