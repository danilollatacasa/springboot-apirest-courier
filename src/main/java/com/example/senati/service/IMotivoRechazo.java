package com.example.senati.service;

import com.example.senati.model.entity.MotivoRechazo;

import java.util.List;

public interface IMotivoRechazo {

    List<MotivoRechazo> getAll();
    MotivoRechazo findById(Integer id);
    MotivoRechazo save(MotivoRechazo motivoRechazo);
    void delete(MotivoRechazo motivoRechazo);

}
