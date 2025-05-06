package com.example.senati.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_jornada")
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fecha")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "hora_inicio")
    private LocalTime  horaInicio;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column(name = "hora_fin")
    private LocalTime horaFin;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
