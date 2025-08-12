package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverytech.delivery.entity.Restaurant;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {

}
