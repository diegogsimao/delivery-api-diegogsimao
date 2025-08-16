package com.deliverytech.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deliverytech.delivery.entity.Restaurant;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findAllByCategory(String category);

    @Query("SELECT r FROM Restaurant r WHERE r.active = :active")
    List<Restaurant> findByActive(boolean active);

    boolean existsById(Long id);

    List<Restaurant> findAllByCep(String cep);
}
