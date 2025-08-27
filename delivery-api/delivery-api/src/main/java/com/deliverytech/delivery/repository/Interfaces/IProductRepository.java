package com.deliverytech.delivery.repository.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.deliverytech.delivery.entity.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByAvailableTrueAndRestaurantsId(Long restaurantId);

    List<Product> findAllBycategoryName(@Param("categoryName") String categoryName);

    Product findByRestaurantsId(Long restaurantId);
}