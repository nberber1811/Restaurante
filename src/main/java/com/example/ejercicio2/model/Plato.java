package com.example.ejercicio2.model;

import jakarta.persistence.*;

/** 
 * @author Nicolás
 * @version 1.0
 */

// Entidad y nombre de la tabla
@Entity
@Table(name = "platos")
public class Plato {

	// Atributos
	@Column(name = "idPlato")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlato;

	private String nombre;
	
    private double precio;
    
    private boolean disponibilidad;

    /**
     * Constructor vacío.
     */
    public Plato() {
        super();
    }

    /**
     * Constructor con parámetros.
     */
    public Plato(Long id, String nombre, double precio, boolean disponibilidad) {
    	super();
    	this.idPlato = id;
    	this.nombre = nombre;
    	this.precio = precio;
    	this.disponibilidad = disponibilidad;
    }

    /**
     * Getter-Setter
     */
	public Long getIdPlato() { return idPlato; }
	public void setIdPlato(Long id) { idPlato = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public double getPrecio() { return precio; }
	public void setPrecio(double precio) { this.precio= precio; }
	public boolean getDisponibilidad() { return disponibilidad; }
	public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad; }
}