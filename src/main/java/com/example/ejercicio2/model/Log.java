package com.example.ejercicio2.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLog")
    private Long idLog;

    @Column(name = "accion")
    private String accion;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    // Constructores
    public Log() {}

    public Log(String accion) {
        this.accion = accion;
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getIdLog() { return idLog; }
    public void setIdLog(Long idLog) { this.idLog = idLog; }
    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}