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

import com.example.ejercicio2.model.Cliente;
import com.example.ejercicio2.service.implementacion.IClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private final IClienteService clienteService;

	/**
	 * Inyección de dependencias por constructor.
	 * 
	 * @param productoService servicio de productos
	 */
	public ClienteController(IClienteService productoService) {
		this.clienteService = productoService;
	}

	@GetMapping
	public List<Cliente> listarProductos() {
		return clienteService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Cliente> obtener(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	@PostMapping
	public Cliente crearProductos(@RequestBody Cliente p) {
		return clienteService.save(p);
	}
	
	@PutMapping("/{id}")
	public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente p) {
		p.setIdCliente(id);
		return clienteService.save(p);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) {
		clienteService.deleteById(id);
	}
}