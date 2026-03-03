package com.example.ejercicio2.service.implementacion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ejercicio2.model.Log;
import com.example.ejercicio2.model.Pedido;
import com.example.ejercicio2.repository.LogRepository;
import com.example.ejercicio2.repository.PedidoRepository;
import com.example.ejercicio2.service.interfaz.PedidoService;

import jakarta.transaction.Transactional;

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
public class IPedidoService implements PedidoService {
	private final PedidoRepository pedidoRepository;
    private final LogRepository logRepository;

    public IPedidoService(PedidoRepository pedidoRepository, LogRepository logRepository) {
        this.pedidoRepository = pedidoRepository;
        this.logRepository = logRepository;
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    @Transactional
    public Pedido save(Pedido pedido) {
        // Guardar pedido
        Pedido savedPedido = pedidoRepository.save(pedido);

        // Crear log automáticamente
        Log log = new Log();
        log.setAccion("Pedido creado: ID " + savedPedido.getIdPedido());
        log.setFecha(LocalDateTime.now());
        logRepository.save(log);

        return savedPedido;
    }

    @Override
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}