package com.example.ejercicio2.service.implementacion;

import com.example.ejercicio2.model.Cliente;
import com.example.ejercicio2.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import com.example.ejercicio2.service.interfaz.ClienteService;

import java.util.List;
import java.util.Optional;

@Service
public class IClienteService implements ClienteService {

	private final ClienteRepository clienteRepository;

    public IClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
