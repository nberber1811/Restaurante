package com.example.ejercicio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio2.model.Plato;

public interface PlatoRepository extends JpaRepository<Plato, Long>{}