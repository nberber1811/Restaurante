package com.example.ejercicio2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.example.ejercicio2.model.Pedido;
import com.example.ejercicio2.model.Cliente;
import com.example.ejercicio2.model.Plato;
import com.example.ejercicio2.repository.PedidoRepository;
import com.example.ejercicio2.repository.ClienteRepository;
import com.example.ejercicio2.repository.PlatoRepository;

// Controlador de pedidos
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	// Atributos de repositorios pedido y foreigns
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PlatoRepository platoRepository;

    // Constructor
    public PedidoController(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, PlatoRepository platoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.platoRepository = platoRepository;
    }

    // Listar todos los pedidos
    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    // Obtener pedido por id
    @GetMapping("/{id}")
    public Optional<Pedido> obtener(@PathVariable Long id) {
        return pedidoRepository.findById(id);
    }

    // Crear pedido
    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {

    	// Verificamos si el cliente existe a traves de su ID
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        
        // Verificamos si el plato existe a traves de su ID
        Plato plato = platoRepository.findById(pedido.getPlato().getIdPlato()).orElseThrow(() -> new RuntimeException("Plato no encontrado"));

        // Asignamos cliente, plato y cantidad a la variable pedido
        pedido.setCliente(cliente);
        pedido.setPlato(plato);
        pedido.setCantidad(pedido.getCantidad());

        return pedidoRepository.save(pedido);
    }

    // Actualizar pedido
    @PutMapping("/{id}")
    public Pedido actualizar(@PathVariable Long id, @RequestBody Pedido pedidoAct) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        Cliente cliente = clienteRepository.findById(pedidoAct.getCliente().getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Plato plato = platoRepository.findById(pedidoAct.getPlato().getIdPlato()).orElseThrow(() -> new RuntimeException("Plato no encontrado"));

        pedido.setCliente(cliente);
        pedido.setPlato(plato);
        pedido.setCantidad(pedidoAct.getCantidad());

        return pedidoRepository.save(pedido);
    }

    // Eliminar pedido
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
    }
}
