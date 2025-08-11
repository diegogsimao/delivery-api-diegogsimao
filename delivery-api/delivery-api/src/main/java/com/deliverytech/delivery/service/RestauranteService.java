package com.deliverytech.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Restaurante;
import com.deliverytech.delivery.repository.IRestauranteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestauranteService {

    @Autowired
    private IRestauranteRepository restauranteRepository;

    // Injeta a dependência
    public RestauranteService(IRestauranteRepository _RestauranteRepository) {
        this.restauranteRepository = _RestauranteRepository;
    }

    // Criação de um novo restaurante
    public Restaurante create(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    // Atualização de um restaurante
    public Restaurante update(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    // Exclusão de um restaurante
    public void delete(Long id) {
        restauranteRepository.deleteById(id);
    }

    public List<Restaurante> findAll() {
        return restauranteRepository.findAll();
    }
}
