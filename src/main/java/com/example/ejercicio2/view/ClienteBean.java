package com.example.ejercicio2.view;

import java.io.Serializable;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.PrimeFaces;

import com.example.ejercicio2.model.Cliente;
import com.example.ejercicio2.model.Log;
import com.example.ejercicio2.model.Pedido;
import com.example.ejercicio2.repository.ClienteRepository;
import com.example.ejercicio2.repository.LogRepository;
import com.example.ejercicio2.repository.PedidoRepository;

@Named
@ViewScoped
public class ClienteBean implements Serializable {
    
    @Inject
    private ClienteRepository clienteRepo;
    
    @Inject
    private LogRepository logRepo;
    
    @Inject
    private PedidoRepository pedidoRepo;

    @Inject
    private PedidoBean pedidoBean;

    private List<Cliente> clientes;
    private Cliente nuevoCliente = new Cliente();
    private Cliente clienteEditado;

    @PostConstruct
    public void init() {
        clientes = clienteRepo.findAll();
    }

    public void guardar() {
        clienteRepo.save(nuevoCliente);
        logRepo.save(new Log("Se creó el cliente: " + nuevoCliente.getNombre()));
        nuevoCliente = new Cliente();
        refrescarTodo();
    }

    public void eliminar(Long id) {
        try {
            Cliente cliente = clienteRepo.findById(id).orElse(null);
            if (cliente != null) {
                // Borramos pedidos asociados manualmente
                List<Pedido> pedidosDelCliente = pedidoRepo.findAll().stream()
                    .filter(p -> p.getCliente().getIdCliente().equals(id))
                    .toList();
                
                pedidoRepo.deleteAll(pedidosDelCliente);
                clienteRepo.deleteById(id);

                logRepo.save(new Log("CLIENTE ELIMINADO: ID " + id));
                refrescarTodo();

                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Borrado", "Cliente eliminado"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void prepararEdicion(Cliente c) {
        this.clienteEditado = c;
    }

    public void actualizar() {
        try {
            clienteRepo.save(clienteEditado);
            logRepo.save(new Log("CLIENTE MODIFICADO: " + clienteEditado.getNombre()));
            
            refrescarTodo();
            
            PrimeFaces.current().executeScript("PF('dlgEditar').hide()");
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Cambios guardados"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refrescarTodo() {
        this.clientes = clienteRepo.findAll();
        if (pedidoBean != null) {
            pedidoBean.refrescarListas();
        }
    }

    // --- GETTERS Y SETTERS ---
    public List<Cliente> getClientes() { return clientes; }
    public void setClientes(List<Cliente> clientes) { this.clientes = clientes; }

    public Cliente getNuevoCliente() { return nuevoCliente; }
    public void setNuevoCliente(Cliente nuevoCliente) { this.nuevoCliente = nuevoCliente; }

    public Cliente getClienteEditado() { return clienteEditado; }
    public void setClienteEditado(Cliente clienteEditado) { this.clienteEditado = clienteEditado; }
}