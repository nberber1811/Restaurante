package com.example.ejercicio2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio2.model.Plato;
import com.example.ejercicio2.repository.PlatoRepository;

@RestController
@RequestMapping("/api/platos")
public class PlatoController {
	private final PlatoRepository productoRepository;

	/**
	 * Inyección de dependencias por constructor.
	 * 
	 * @param productoService servicio de productos
	 */
	public PlatoController(PlatoRepository productoService) {
		this.productoRepository = productoService;
	}

	@GetMapping
	public List<Plato> listarProductos() {
		return productoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Plato> obtener(@PathVariable Long id) {
		return productoRepository.findById(id);
	}
	
	@PostMapping
	public Plato crearProductos(@RequestBody Plato p) {
		return productoRepository.save(p);
	}
	
	@PutMapping("/{id}")
	public Plato actualizar(@PathVariable Long id, @RequestBody Plato p) {
		p.setIdPlato(id);
		return productoRepository.save(p);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		productoRepository.deleteById(id);
	}
}