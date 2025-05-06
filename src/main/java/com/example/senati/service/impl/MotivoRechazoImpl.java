package com.example.senati.service.impl;

import com.example.senati.model.entity.MotivoRechazo;
import com.example.senati.model.repository.MotivoRechazoRepository;
import com.example.senati.service.IMotivoRechazo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MotivoRechazoImpl implements IMotivoRechazo {

    @Autowired
    MotivoRechazoRepository motivoRechazoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<MotivoRechazo> getAll(){
        return motivoRechazoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public MotivoRechazo findById(Integer id){
        return motivoRechazoRepository.findById(id).orElse(null);
    }

    @Override
    public MotivoRechazo save(MotivoRechazo motivoRechazo){
        return motivoRechazoRepository.save(motivoRechazo);
    }
    @Override
    public void delete(MotivoRechazo motivoRechazo){
        motivoRechazoRepository.delete(motivoRechazo);
    }



}
