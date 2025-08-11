package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deliverytech.delivery.entity.Restaurante;

public interface IRestauranteRepository extends JpaRepository<Restaurante, Long> {

}
