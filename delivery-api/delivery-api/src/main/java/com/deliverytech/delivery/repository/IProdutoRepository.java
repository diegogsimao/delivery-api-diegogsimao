package com.deliverytech.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deliverytech.delivery.entity.Product;

public interface IProdutoRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.restaurants.id = :restaurant_id" +
            " AND p.available = true")
    List<Product> findAllByRestaurantActiveById(Long restaurant_id);

    List<Product> findAllByCategoryId(Long category_id);
}