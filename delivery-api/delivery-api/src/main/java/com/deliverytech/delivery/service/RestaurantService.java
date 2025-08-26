package com.deliverytech.delivery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.DTOs.Requests.RestaurantDTO;
import com.deliverytech.delivery.DTOs.Response.RestaurantResponseDTO;
import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.exceptions.EntityNotFoundException;
import com.deliverytech.delivery.mapper.RestaurantMapper;
import com.deliverytech.delivery.repository.IRestaurantRepository;
import com.deliverytech.delivery.service.Interfaces.IRestaurantService;

import jakarta.transaction.Transactional;

@Service
public class RestaurantService implements IRestaurantService {

    private IRestaurantRepository restaurantRepository;
    private RestaurantMapper restaurantMapper;

    @Autowired
    public RestaurantService(IRestaurantRepository _restaurantRepository,
            RestaurantMapper restaurantMapper) {
        this.restaurantRepository = _restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    public List<RestaurantResponseDTO> findAll() {

        return restaurantMapper.toListFromRestaurantResponseDTOs(
                restaurantRepository.findAll());
    }

    // Criação de um novo restaurante
    @Transactional
    public RestaurantResponseDTO create(RestaurantDTO restaurant) {

        if (restaurant == null) {
            throw new EntityNotFoundException("restaurante não pode ser nulo");
        }

        boolean nomeExiste = restaurantRepository.findByName(
                restaurant.getName());

        // if (nomeExiste) {
        // throw new ConflictException(
        // "Já existe um restaurante com este nome",
        // "nome",
        // restaurant.getName());
        // }

        Restaurant restaurante = restaurantMapper.toSource(restaurant);
        return restaurantMapper.toEntityFromResponseDTO(restaurantRepository.save(restaurante));
    }

    public RestaurantResponseDTO findById(Long id) {
        // Busca um restaurante pelo ID
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + id);
        }

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado: " + id));

        return restaurantMapper.toEntityFromResponseDTO(restaurant);

    }

    public List<RestaurantResponseDTO> getAllByCategory(String category) {

        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser nula ou vazia");
        }

        List<Restaurant> lstRestaurant = restaurantRepository.findAllByCategory(category);
        return restaurantMapper.toListFromRestaurantResponseDTOs(lstRestaurant);
    }

    // Atualização de um restaurante
    @Transactional
    public RestaurantResponseDTO update(RestaurantDTO restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("restaurant não pode ser nulo");
        }

        Restaurant existingRestaurant = restaurantRepository.findById(restaurant.getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado: " + restaurant.getId()));

        Restaurant updatedRestaurant = restaurantMapper.toSource(restaurant);
        updatedRestaurant.setId(existingRestaurant.getId());

        return restaurantMapper.toEntityFromResponseDTO(restaurantRepository.save(updatedRestaurant));
    }

    @Transactional
    public RestaurantResponseDTO updateActive(RestaurantDTO restaurant) {

        if (restaurant == null) {
            throw new EntityNotFoundException("restaurant não pode ser nulo");
        }

        Restaurant _restaurant = restaurantRepository.findById(restaurant.getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado: " + restaurant.getId()));
        restaurant.setActive(!_restaurant.isActive());

        return restaurantMapper
                .toEntityFromResponseDTO(restaurantRepository.save(restaurantMapper.toSource(restaurant)));
    }

    // Exclusão de um restaurant
    @Transactional
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    public double calculateDeliveryFee(Long id, String cep) {
        // Restaurant restaurant = findById(id);
        // if (restaurant == null) {
        // throw new IllegalArgumentException("Restaurante não encontrado: " + id);
        // }

        // BigDecimal baseFee = restaurant.getDeliveryFee();
        // if (baseFee == null) {
        // throw new IllegalArgumentException("Taxa de entrega não definida para o
        // restaurante: " + id);
        // }

        // if (cep == null || cep.isEmpty()) {
        // throw new IllegalArgumentException("CEP não pode ser nulo ou vazio");
        // }

        // BigDecimal distanceFee = calculateDistanceFee(cep);

        // return baseFee.add(distanceFee.multiply(baseFee));

        return 0.00;
    }

    private double calculateDistanceFee(String cep) {
        double distanceFee = 5.00; // Taxa fixa de R$5,00
        return distanceFee;
    }

    public List<RestaurantResponseDTO> findByActive() {

        return restaurantMapper.toListFromRestaurantResponseDTOs(restaurantRepository.findByActive());
    }

    public List<RestaurantResponseDTO> findByCep(String cep) {
        return restaurantMapper.toListFromRestaurantResponseDTOs(restaurantRepository.findAllByCep(cep));
    }

    public Double calcularTaxaEntrega(Long id, String cep) {
        // Restaurant restaurant = restaurantRepository.findById(id)
        // .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado: "
        // + id));
        // if (restaurant == null) {
        // throw new EntityNotFoundException("Restaurante não encontrado: " + id);
        // }

        // Double baseFee = restaurant.getDeliveryFee();
        // if (baseFee == null) {
        // throw new IllegalArgumentException("Taxa de entrega não definida para o
        // restaurante: " + id);
        // }

        // if (cep == null || cep.isEmpty()) {
        // throw new IllegalArgumentException("CEP não pode ser nulo ou vazio");
        // }

        // double distanceFee = calculateDistanceFee(cep);

        // return baseFee.add(distanceFee * baseFee).doubleValue();

        return 0.00;
    }
}