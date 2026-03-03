package com.example.ejercicio2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 * Este es el punto de entrada del programa. 
 * Se encarga de inicializar el contexto de Spring y lanzar la aplicación web.
 *
 * @author Nicolás
 * @version 1.0
 */

@SpringBootApplication
public class Ejercicio2Application {

    /**
     * Método principal que arranca la aplicación Spring Boot.
     *
     * @param args argumentos de línea de comandos
     */

	public static void main(String[] args) {
        SpringApplication.run(Ejercicio2Application.class, args);
    }
}