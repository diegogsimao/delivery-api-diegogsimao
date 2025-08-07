package com.deliverytech.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deliverytech.delivery.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.categoria LIKE %:categoria%")
    List<Produto> findByCategoria(String categoria);

    @Query("SELECT p FROM Produto p WHERE p.restaurante.id = :restauranteId")
    List<Produto> findByRestauranteId(Long restauranteId);

    @Query("SELECT p FROM Produto p WHERE p.disponivel = true")
    List<Produto> findByDisponibilidadeTrue();

}