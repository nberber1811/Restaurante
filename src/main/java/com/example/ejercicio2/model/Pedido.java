package com.example.ejercicio2.model;

import jakarta.persistence.*;

/** 
 * @author Nicolás
 * @version 1.0
 */

// Entidad y nombre de la tabla
@Entity
@Table(name = "pedidos")
public class Pedido {

	// Atributos
	@Column(name = "idPedido")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	@ManyToOne
	@JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "idPlato", referencedColumnName = "idPlato")
	private Plato plato;
	
    @Column(name = "cantidad")
    private int cantidad;
    
    /**
     * Constructor vacío.
     */
    public Pedido() {
        super();
    }

    /**
     * Constructor con parámetros.
     */
    public Pedido(Long id, Cliente cliente, Plato plato, int cantidad) {
    	super();
    	this.idPedido = id;
    	this.cliente = cliente;
    	this.plato = plato;
    	this.cantidad = cantidad;
    }
  
    /**
     * Getter-Setter
     */
	public Long getIdPedido() { return idPedido; }
	public void setIdPedido(Long id) { idPedido = id; }
	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }
	public Plato getPlato() { return plato; }
	public void setPlato(Plato plato) { this.plato = plato;	}
	public int getCantidad() { return cantidad; }
	public void setCantidad(int cantidad) { this.cantidad = cantidad; }
	
	public Double getTotal() {
		if (plato != null && cantidad != 0) {
			return plato.getPrecio() * cantidad;
	    }
	    return 0.0;
	}
	    
	
}