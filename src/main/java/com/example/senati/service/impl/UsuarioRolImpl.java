package com.example.senati.service.impl;

import com.example.senati.model.entity.UsuarioRol;
import com.example.senati.model.entity.UsuarioRolPK;
import com.example.senati.model.repository.UsuarioRolRepository;
import com.example.senati.service.IUsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioRolImpl implements IUsuarioRol {

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    public List<UsuarioRol> obtenerTodosRolesPorUsuario() {
        return usuarioRolRepository.findAll();
    }

    @Override
    public List<UsuarioRol> obtenerRolesPorUsuario(Integer usuarioId) {
        return usuarioRolRepository.findByUsuarioId(usuarioId);
    }
    @Override
    public void asignarRolAUsuario(UsuarioRol usuarioRol) {
        UsuarioRolPK pk = new UsuarioRolPK();
        pk.setUsuarioId(usuarioRol.getUsuario().getId());
        pk.setRolId(usuarioRol.getRol().getId());
        usuarioRol.setId(pk);
        usuarioRolRepository.save(usuarioRol);
    }
    @Override
    public void eliminarRolDeUsuario(Integer usuarioId, Integer rolId){
        usuarioRolRepository.deleteByUsuarioAndRolId(usuarioId,rolId);
    }
}
