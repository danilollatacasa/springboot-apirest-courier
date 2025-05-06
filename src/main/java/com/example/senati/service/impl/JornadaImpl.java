package com.example.senati.service.impl;

import com.example.senati.model.entity.Jornada;
import com.example.senati.model.entity.Rol;
import com.example.senati.model.entity.Usuario;
import com.example.senati.model.entity.UsuarioRol;
import com.example.senati.model.repository.JornadaRepository;
import com.example.senati.model.repository.UsuarioRolRepository;
import com.example.senati.service.IJornada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class JornadaImpl implements IJornada {

    @Autowired
    private JornadaRepository jornadaRepository;
    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    public Jornada iniciarJornada(Jornada jornada) {

        Usuario  usuario = jornada.getUsuario();
        List<UsuarioRol> roles = usuarioRolRepository.findByUsuarioId(usuario.getId());



        boolean esRepartidor = roles.stream()
                .map(UsuarioRol::getRol)
                .map(Rol::getRolName)
                .anyMatch(nombreRol -> nombreRol.equalsIgnoreCase("repartidor"));

        if(!esRepartidor) {
            throw new RuntimeException("Solo un repartidor puede iniciar jornada.");
        }

        jornada.setFecha(LocalDate.now());
        jornada.setHoraInicio(LocalTime.now());
        jornada.setHoraFin(null);
        return jornadaRepository.save(jornada);
    }

    @Override
    public List<Jornada> listarJornadas() {
        return  jornadaRepository.findAll();
    }

    @Override
    public Jornada finalizarJornada(Integer id) {
        Jornada jornada = jornadaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jornada no encontrada"));

        if(jornada.getHoraFin() != null) {
            throw new RuntimeException("La jornada ya fue finalizada");
        }

        jornada.setHoraFin(LocalTime.now());
        return jornadaRepository.save(jornada);
    }


    @Override
    public Jornada getJornadaById(Integer id) {
        return jornadaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarJornada(Jornada jornada) {
        jornadaRepository.delete(jornada);
    }

}
