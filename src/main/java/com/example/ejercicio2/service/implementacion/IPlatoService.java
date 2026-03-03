package com.example.ejercicio2.service.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ejercicio2.model.Plato;
import com.example.ejercicio2.repository.PlatoRepository;
import com.example.ejercicio2.service.interfaz.PlatoService;

/**
 * Implementación del servicio de productos.
 * Esta clase simula una capa de acceso a datos utilizando una lista en memoria,
 * sin conexión a base de datos real. Permite realizar operaciones básicas
 * sobre objetos Producto, como listar, buscar por id o añadir nuevos.
 *
 * @author Nicolás
 * @version 1.0
 */
@Service
public class IPlatoService implements PlatoService {

	private final PlatoRepository platoRepository;

    public IPlatoService(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }

    @Override
    public List<Plato> findAll() {
        return platoRepository.findAll();
    }

    @Override
    public Optional<Plato> findById(Long id) {
        return platoRepository.findById(id);
    }

    @Override
    public Plato save(Plato plato) {
        return platoRepository.save(plato);
    }

    @Override
    public void deleteById(Long id) {
        platoRepository.deleteById(id);
    }
}
