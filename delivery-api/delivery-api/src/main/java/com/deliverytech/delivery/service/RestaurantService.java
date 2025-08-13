package com.deliverytech.delivery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.repository.IRestaurantRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestaurantService {

    private IRestaurantRepository restauranteRepository;

    @Autowired
    public RestaurantService(IRestaurantRepository _RestauranteRepository) {
        this.restauranteRepository = _RestauranteRepository;
    }

    // Criação de um novo restaurante
    public Restaurant create(Restaurant restaurante) {
        return restauranteRepository.save(restaurante);
    }

    // Atualização de um restaurante
    public Restaurant update(Restaurant restaurante) {
        return restauranteRepository.save(restaurante);
    }

    // Exclusão de um restaurante
    public void delete(Long id) {
        restauranteRepository.deleteById(id);
    }

    public List<Restaurant> findAll() {
        return restauranteRepository.findAll();
    }

}
