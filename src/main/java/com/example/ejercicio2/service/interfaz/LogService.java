package com.example.ejercicio2.service.interfaz;

import java.util.List;

import com.example.ejercicio2.model.Log;

/**
* @author Nicolás
* @version 1.0
*/

public interface LogService{
	List<Log> findAll();
    Log save(Log log);
}