package com.example.ejercicio2.model;

import java.util.List;

import jakarta.persistence.*;

/** 
 * @author Nicolás
 * @version 1.0
 */

// Entidad y nombre de la tabla
@Entity
@Table(name = "clientes")
public class Cliente {

	// Atributos
	@Column(name = "idCliente")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;

	private String nombre;
	
    private String telefono;

    /**
     * Constructor vacío.
     */
    public Cliente() {
        super();
    }

    /**
     * Constructor con parámetros.
     *
     */
    public Cliente(Long id, String nombre, String telefono) {
    	super();
    	this.idCliente = id;
    	this.nombre = nombre;
    	this.telefono = telefono;
    }

    /**
     * Getter-Setter
     */
	public Long getIdCliente() { return idCliente; }
	public void setIdCliente(Long id) { idCliente = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
}