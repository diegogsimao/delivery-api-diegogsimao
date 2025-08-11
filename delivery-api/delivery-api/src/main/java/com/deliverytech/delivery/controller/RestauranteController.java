package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.deliverytech.delivery.entity.Restaurante;
import com.deliverytech.delivery.service.RestauranteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping("/restaurantes")
    public Restaurante createRestaurante(@RequestBody Restaurante restaurante) {
        return restauranteService.create(restaurante);
    }

    @PutMapping("/restaurantes/{id}")
    public Restaurante updateRestaurante(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        restaurante.setId(id);
        return restauranteService.update(restaurante);
    }

    @DeleteMapping("/restaurantes/{id}")
    public void deleteRestaurante(@PathVariable Long id) {
        restauranteService.delete(id);
    }

    @GetMapping("/restaurantes")
    public List<Restaurante> getAllRestaurantes() {
        return restauranteService.findAll();
    }
}
