package com.example.ejercicio2.service.interfaz;

import java.util.List;
import java.util.Optional;

import com.example.ejercicio2.model.Pedido;

/**
* @author Nicolás
* @version 1.0
*/

public interface PedidoService{
	List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    Pedido save(Pedido pedido);
    void deleteById(Long id);
}
