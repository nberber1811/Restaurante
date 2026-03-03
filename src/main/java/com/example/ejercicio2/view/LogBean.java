package com.example.ejercicio2.view;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import com.example.ejercicio2.repository.LogRepository;
import com.example.ejercicio2.model.Log;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class LogBean implements Serializable {
    @Inject private LogRepository logRepo;
    private List<Log> logs;
    
    @PostConstruct
    public void init() {
        refrescar();
    }

    public void refrescar() {
    	logs = logRepo.findAll();
        java.util.Collections.reverse(logs);
    }
    
    public List<Log> getLogs() { return logs; }
}