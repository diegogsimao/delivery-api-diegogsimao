package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery.DTOs.RestaurantDTO;
import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.mapper.RestaurantMapper;
import com.deliverytech.delivery.service.RestaurantService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class RestauranteController {

    private RestaurantService restaurantService;
    private RestaurantMapper restaurantMapper;

    @Autowired
    public RestauranteController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @PostMapping("/restaurantes")
    public RestaurantDTO createRestaurante(
            @RequestBody RestaurantDTO restauranteDTO) {

        Restaurant restaurant = restaurantMapper.toSource(restauranteDTO);
        return restaurantMapper.toTarget(restaurantService.create(restaurant));
    }

    @PutMapping("/restaurantes/{id}")
    public RestaurantDTO updateRestaurante(
            @PathVariable Long id,
            @RequestBody RestaurantDTO restaurantDTO) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + id);
        }

        Restaurant restaurant = restaurantMapper.toSource(restaurantDTO);
        return restaurantMapper.toTarget(restaurantService.update(restaurant));
    }

    @DeleteMapping("/restaurantes/{id}")
    public void deleteRestaurante(@PathVariable Long id) {
        restaurantService.delete(id);
    }

}
