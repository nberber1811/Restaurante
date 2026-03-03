package com.example.ejercicio2.service.interfaz;

import java.util.List;
import java.util.Optional;

import com.example.ejercicio2.model.Cliente;

/**
* @author Nicolás
* @version 1.0
*/

public interface ClienteService {
	List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    void deleteById(Long id);
}