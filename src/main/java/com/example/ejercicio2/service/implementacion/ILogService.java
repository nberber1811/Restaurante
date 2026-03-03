package com.example.ejercicio2.service.implementacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ejercicio2.model.Log;
import com.example.ejercicio2.repository.LogRepository;
import com.example.ejercicio2.service.interfaz.LogService;

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
public class ILogService implements LogService {
	
	private final LogRepository logRepository;

    public ILogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }
}
