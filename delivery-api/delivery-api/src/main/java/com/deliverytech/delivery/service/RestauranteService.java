package com.deliverytech.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Restaurante;
import com.deliverytech.delivery.repository.RestauranteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante Create(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public Restaurante Update(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public void Delete(Long id) {
        restauranteRepository.deleteById(id);
    }

    public boolean ValidarDadosRestaurante(Restaurante restaurante) {

        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante não pode ser nulo");
        }
        // Lógica para validar os dados do restaurante
        return true;
    }

    public void AtivarRestaurante(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        restaurante.setAtivo(true);
        restauranteRepository.save(restaurante);
    }

    public void DesativarRestaurante(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        restaurante.setAtivo(false);
        restauranteRepository.save(restaurante);
    }
}
