package com.deliverytech.delivery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.DTOs.RestaurantDTO;
import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.repository.IRestaurantRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestaurantService {

    @Autowired
    private IRestaurantRepository restauranteRepository;

    // Injeta a dependência
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

    public List<RestaurantDTO> findAll() {
        return restauranteRepository.findAll().stream()
                .map(this::ConvertEntityToDTO)
                .collect(Collectors.toList());
    }

    private RestaurantDTO ConvertEntityToDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO();
        dto.setName(restaurant.getName());
        dto.setDescription(restaurant.getDescription());
        return dto;
    }

}
