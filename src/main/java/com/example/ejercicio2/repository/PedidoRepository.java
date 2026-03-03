package com.example.ejercicio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio2.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{}