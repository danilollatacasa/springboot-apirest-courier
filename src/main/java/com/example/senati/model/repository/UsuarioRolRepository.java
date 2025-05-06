package com.example.senati.model.repository;

import com.example.senati.model.entity.UsuarioRol;
import com.example.senati.model.entity.UsuarioRolPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolPK> {

    List<UsuarioRol> findByUsuarioId(Integer usuarioId);

    @Transactional
    @Modifying
    @Query("DELETE FROM UsuarioRol ur WHERE ur.id.usuarioId = :usuarioId AND ur.id.rolId = :rolId")
    void deleteByUsuarioAndRolId(@Param("usuarioId") Integer usuarioId, @Param("rolId") Integer rolId);
}
