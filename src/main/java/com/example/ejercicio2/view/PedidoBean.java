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

import com.example.ejercicio2.model.Pedido;
import com.example.ejercicio2.model.Cliente;
import com.example.ejercicio2.model.Plato;
import com.example.ejercicio2.model.Log;
import com.example.ejercicio2.repository.PedidoRepository;
import com.example.ejercicio2.repository.ClienteRepository;
import com.example.ejercicio2.repository.PlatoRepository;
import com.example.ejercicio2.repository.LogRepository;

@Named
@ViewScoped
public class PedidoBean implements Serializable {

    @Inject
    private PedidoRepository pedidoRepo;

    @Inject
    private ClienteRepository clienteRepo;

    @Inject
    private PlatoRepository platoRepo;

    @Inject
    private LogRepository logRepo;

    private List<Pedido> pedidos;
    private List<Cliente> clientes;
    private List<Plato> platos;

    private Pedido nuevoPedido = new Pedido();
    private Pedido pedidoEditado;
    
    private Long idClienteSel;
    private Long idPlatoSel;

    @PostConstruct
    public void init() {
        refrescarListas();
    }

    public void refrescarListas() {
        this.pedidos = pedidoRepo.findAll();
        this.clientes = clienteRepo.findAll();
        this.platos = platoRepo.findAll();
    }

    public void guardar() {
        try {
            Cliente c = clienteRepo.findById(idClienteSel).orElse(null);
            Plato p = platoRepo.findById(idPlatoSel).orElse(null);

            nuevoPedido.setCliente(c);
            nuevoPedido.setPlato(p);

            pedidoRepo.save(nuevoPedido);
            logRepo.save(new Log("PEDIDO CREADO: Cliente " + c.getNombre() + " pidió " + p.getNombre()));

            // Resetear formulario
            nuevoPedido = new Pedido();
            idClienteSel = null;
            idPlatoSel = null;
            
            refrescarListas();

            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Pedido registrado correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el pedido"));
        }
    }

    public void eliminar(Long id) {
        try {
            pedidoRepo.deleteById(id);
            logRepo.save(new Log("PEDIDO ELIMINADO: ID " + id));
            
            refrescarListas();

            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Borrado", "Pedido eliminado con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar el pedido"));
        }
    }

    public void prepararEdicion(Pedido p) {
        this.pedidoEditado = p;
        // Cargamos los IDs actuales para los combos del diálogo
        this.idClienteSel = p.getCliente().getIdCliente();
        this.idPlatoSel = p.getPlato().getIdPlato();
    }

    public void actualizar() {
        try {
            Cliente c = clienteRepo.findById(idClienteSel).orElse(null);
            Plato p = platoRepo.findById(idPlatoSel).orElse(null);

            pedidoEditado.setCliente(c);
            pedidoEditado.setPlato(p);

            pedidoRepo.save(pedidoEditado);
            logRepo.save(new Log("PEDIDO MODIFICADO: ID " + pedidoEditado.getIdPedido()));

            refrescarListas();

            PrimeFaces.current().executeScript("PF('dlgEditarPedido').hide()");

            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Pedido modificado con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al actualizar el pedido"));
        }
    }
    
    public Double getSumaTotalVentas() {
        return pedidos.stream()
                      .mapToDouble(p -> p.getPlato().getPrecio() * p.getCantidad())
                      .sum();
    }

    // --- GETTERS Y SETTERS ---
    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }

    public List<Cliente> getClientes() { return clientes; }
    public void setClientes(List<Cliente> clientes) { this.clientes = clientes; }

    public List<Plato> getPlatos() { return platos; }
    public void setPlatos(List<Plato> platos) { this.platos = platos; }

    public Pedido getNuevoPedido() { return nuevoPedido; }
    public void setNuevoPedido(Pedido nuevoPedido) { this.nuevoPedido = nuevoPedido; }

    public Pedido getPedidoEditado() { return pedidoEditado; }
    public void setPedidoEditado(Pedido pedidoEditado) { this.pedidoEditado = pedidoEditado; }

    public Long getIdClienteSel() { return idClienteSel; }
    public void setIdClienteSel(Long idClienteSel) { this.idClienteSel = idClienteSel; }

    public Long getIdPlatoSel() { return idPlatoSel; }
    public void setIdPlatoSel(Long idPlatoSel) { this.idPlatoSel = idPlatoSel; }
}