package com.example.ejercicio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio2.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{}