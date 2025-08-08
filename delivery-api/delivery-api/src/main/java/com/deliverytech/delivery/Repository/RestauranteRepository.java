package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deliverytech.delivery.entity.Restaurante;

import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("SELECT r FROM Restaurante r WHERE r.nome LIKE %:nome%")
    List<Restaurante> findByNome(String nome);

    @Query("SELECT r FROM Restaurante r WHERE r.categori LIKE %:cateogias%")
    List<Restaurante> findByCategory(String category);

    @Query("SELECT r FROM Restaurante r WHERE r.ativo = true")
    List<Restaurante> findByAtivoTrue();

    @Query("SELECT r FROM Restaurante r ORDER BY r.avaliacao DESC")
    List<Restaurante> findByOrdenadoPorAvaliacao();

}
