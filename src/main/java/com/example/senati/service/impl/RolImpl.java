package com.example.senati.service.impl;

import com.example.senati.model.entity.Rol;
import com.example.senati.model.repository.RolRepository;
import com.example.senati.service.IRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolImpl implements IRol {

    @Autowired
    private RolRepository rolRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Rol> getAll(){
        return rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Rol getById(Integer id){
        return rolRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Rol save(Rol rol){
        return rolRepository.save(rol);
    }

    @Transactional
    @Override
    public void delete(Rol rol){
        rolRepository.delete(rol);
    }

}
