package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery.DTOs.RestaurantDTO;
import com.deliverytech.delivery.entity.Restaurant;
import com.deliverytech.delivery.service.RestaurantService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class RestauranteController {

    @Autowired
    private RestaurantService restauranteService;

    @PostMapping("/restaurantes")
    public Restaurant createRestaurante(@RequestBody Restaurant restaurante) {
        return restauranteService.create(restaurante);
    }

    @PutMapping("/restaurantes/{id}")
    public Restaurant updateRestaurante(@PathVariable Long id, @RequestBody Restaurant restaurante) {
        restaurante.setId(id);
        return restauranteService.update(restaurante);
    }

    @DeleteMapping("/restaurantes/{id}")
    public void deleteRestaurante(@PathVariable Long id) {
        restauranteService.delete(id);
    }

    @GetMapping("/restaurantes")
    public List<RestaurantDTO> getAllRestaurantes() {
        return restauranteService.findAll();
    }
}
