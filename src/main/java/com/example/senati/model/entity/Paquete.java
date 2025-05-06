package com.example.senati.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_paquete")
public class Paquete {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="producto")
    private String producto;
    @Column(name="remitente")
    private String remitente;
    @Column(name="destinatario")
    private String destinatario;
    @Column(name="fecha_llegada")
    private LocalDate fechaLlegada;
    @Column(name="fecha_entrega")
    private LocalDate fechaEntrega;
    @Column(name="numero_contacto")
    private String numeroContacto;
    @Column(name="direccion")
    private String direccion;
    @Column(name="latitud")
    private String latitud;
    @Column(name="longitud")
    private String longitud;
    @Column(name="codigo_barra")
    private String codigoBarra;
    @Column(name="codigo_barra_url")
    private String codigoBarraUrl;

    @ManyToOne
    @JoinColumn(name="estado_id")
    private EstadoPaquete estado;
    @ManyToOne
    @JoinColumn(name="distrito_id")
    private Distrito distrito;
}
