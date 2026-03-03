package com.example.ejercicio2.view;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.PrimeFaces;

import com.example.ejercicio2.model.Plato;
import com.example.ejercicio2.model.Log;
import com.example.ejercicio2.model.Pedido;
import com.example.ejercicio2.repository.PlatoRepository;
import com.example.ejercicio2.repository.LogRepository;
import com.example.ejercicio2.repository.PedidoRepository;

@Named
@ViewScoped
public class PlatoBean implements Serializable {

    @Inject
    private PlatoRepository platoRepo;

    @Inject
    private LogRepository logRepo;

    @Inject
    private PedidoRepository pedidoRepo;

    @Inject
    private PedidoBean pedidoBean;

    private List<Plato> platos;
    private Plato nuevoPlato = new Plato();
    private Plato platoEditado;

    @PostConstruct
    public void init() {
        platos = platoRepo.findAll();
    }

    public void guardar() {
        platoRepo.save(nuevoPlato);
        logRepo.save(new Log("Plato añadido: " + nuevoPlato.getNombre()));
        nuevoPlato = new Plato();
        refrescarTodo();
        
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Plato creado correctamente"));
    }

    public void eliminar(Long id) {
        try {
            // 1. Borrado manual de pedidos asociados (Cascada manual)
            List<Pedido> pedidosConEsePlato = pedidoRepo.findAll().stream()
                .filter(p -> p.getPlato().getIdPlato().equals(id))
                .collect(Collectors.toList());
            
            pedidoRepo.deleteAll(pedidosConEsePlato);

            // 2. Borrar el plato
            platoRepo.deleteById(id);
            
            // 3. Log y Refresco
            logRepo.save(new Log("PLATO ELIMINADO: ID " + id));
            refrescarTodo();

            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Plato y sus pedidos eliminados"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el plato"));
        }
    }

    public void prepararEdicion(Plato p) {
        this.platoEditado = p;
    }

    public void actualizar() {
        try {
            platoRepo.save(platoEditado);
            logRepo.save(new Log("PLATO MODIFICADO: " + platoEditado.getNombre()));
            
            refrescarTodo();
            
            // Cerramos el diálogo desde el servidor
            PrimeFaces.current().executeScript("PF('dlgEditarPlato').hide()");
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Plato modificado con éxito"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al actualizar"));
        }
    }

    private void refrescarTodo() {
        this.platos = platoRepo.findAll();
        if (pedidoBean != null) {
            pedidoBean.refrescarListas();
        }
    }

    // --- GETTERS Y SETTERS ---
    public List<Plato> getPlatos() { return platos; }
    public void setPlatos(List<Plato> platos) { this.platos = platos; }

    public Plato getNuevoPlato() { return nuevoPlato; }
    public void setNuevoPlato(Plato nuevoPlato) { this.nuevoPlato = nuevoPlato; }

    public Plato getPlatoEditado() { return platoEditado; }
    public void setPlatoEditado(Plato platoEditado) { this.platoEditado = platoEditado; }
}