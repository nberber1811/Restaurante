package com.example.ejercicio2.service.interfaz;

import java.util.List;
import java.util.Optional;

import com.example.ejercicio2.model.Plato;

/**
* @author Nicolás
* @version 1.0
*/

public interface PlatoService {
	List<Plato> findAll();
    Optional<Plato> findById(Long id);
    Plato save(Plato plato);
    void deleteById(Long id);
}
