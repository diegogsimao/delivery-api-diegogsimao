package com.deliverytech.delivery.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverytech.delivery.entity.Restaurant;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {

    // public List<Restaurant> findByNameContainingIgnoreCase(String name);

    // public List<Restaurant> findTop5ByOrderByNomeAsc();

}
