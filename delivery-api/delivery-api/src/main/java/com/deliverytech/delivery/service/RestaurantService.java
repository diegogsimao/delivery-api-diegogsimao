package com.deliverytech.delivery.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.repository.IRestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantService {

    private IRestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(IRestaurantRepository _restaurantRepository) {
        this.restaurantRepository = _restaurantRepository;
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    // Criação de um novo restaurante
    @Transactional
    public Restaurant create(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("restaurante não pode ser nulo");
        }

        return restaurantRepository.save(restaurant);
    }

    public Restaurant findById(Long id) {
        // Busca um restaurante pelo ID
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + id);
        }

        return restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado: " + id));
    }

    public List<Restaurant> getAllByCategory(String category) {

        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser nula ou vazia");
        }
        return restaurantRepository.findAllByCategory(category);
    }

    public List<Restaurant> getAllByCep(String cep) {
        if (cep == null || cep.isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser nulo ou vazio");
        }
        return restaurantRepository.findAllByCep(cep);
    }

    // Atualização de um restaurante
    @Transactional
    public Restaurant update(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("restaurant não pode ser nulo");
        }

        if (!restaurantRepository.existsById(restaurant.getId())) {
            throw new IllegalArgumentException("restaurante não encontrado: " + restaurant.getId());
        }

        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateActive(Restaurant restaurant) {
        if (restaurant.getId() <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + restaurant.getId());
        }

        Restaurant _restaurant = findById(restaurant.getId());
        restaurant.setActive(!_restaurant.isActive());

        return restaurantRepository.save(restaurant);
    }

    // Exclusão de um restaurant
    @Transactional
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    public BigDecimal calculateDeliveryFee(Long id, String cep) {
        Restaurant restaurant = findById(id);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurante não encontrado: " + id);
        }

        BigDecimal baseFee = restaurant.getDeliveryFee();
        if (baseFee == null) {
            throw new IllegalArgumentException("Taxa de entrega não definida para o restaurante: " + id);
        }

        if (cep == null || cep.isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser nulo ou vazio");
        }

        BigDecimal distanceFee = calculateDistanceFee(cep);

        return baseFee.add(distanceFee.multiply(baseFee));
    }

    private BigDecimal calculateDistanceFee(String cep) {
        BigDecimal distanceFee = new BigDecimal("5.00"); // Taxa fixa de R$5,00
        return distanceFee;
    }

    public List<Restaurant> findByActive() {
        return restaurantRepository.findByActive();
    }

    public List<Restaurant> findByCep(String cep) {
        return restaurantRepository.findAllByCep(cep);
    }

    public List<Restaurant> findByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante não pode ser nulo ou vazio");
        }
        return restaurantRepository.findByName(name);
    }

    public Double calcularTaxaEntrega(Long id, String cep) {
        Restaurant restaurant = findById(id);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurante não encontrado: " + id);
        }

        BigDecimal baseFee = restaurant.getDeliveryFee();
        if (baseFee == null) {
            throw new IllegalArgumentException("Taxa de entrega não definida para o restaurante: " + id);
        }

        if (cep == null || cep.isEmpty()) {
            throw new IllegalArgumentException("CEP não pode ser nulo ou vazio");
        }

        BigDecimal distanceFee = calculateDistanceFee(cep);

        return baseFee.add(distanceFee.multiply(baseFee)).doubleValue();
    }
}