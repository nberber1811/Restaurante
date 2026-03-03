package com.example.ejercicio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio2.model.Log;

public interface LogRepository extends JpaRepository<Log, Long>{}