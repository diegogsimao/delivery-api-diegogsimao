package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery.DTOs.Requests.RestaurantDTO;
import com.deliverytech.delivery.DTOs.Response.RestaurantResponseDTO;
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

    @PostMapping("api/restaurantes")
    public ResponseEntity<RestaurantResponseDTO> createRestaurante(
            @RequestBody RestaurantDTO restauranteDTO) {

        Restaurant restaurant = restaurantMapper.toSource(restauranteDTO);
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTO(restaurantService.create(restaurant)));
    }

    @GetMapping("api/restaurantes/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurante(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + id);
        }

        Restaurant restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTO(restaurant));
    }

    @GetMapping("api/restaurantes")
    public ResponseEntity<List<RestaurantResponseDTO>> getAll() {
        List<Restaurant> restaurantes = restaurantService.findAll();
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTOList(restaurantes));
    }

    @GetMapping("api/restaurantes/categoria/{categoria}")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantesByCategoria(@PathVariable String categoria) {
        List<Restaurant> restaurantes = restaurantService.getAllByCategory(categoria);
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTOList(restaurantes));
    }

    @GetMapping("api/restaurantes/{id}/taxa-entrega/{cep}")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantesByCategoria(
            @PathVariable Long id,
            @PathVariable String cep) {

        Restaurant restaurant = restaurantService.findById(id);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurante não encontrado: " + id);
        }

        List<Restaurant> restaurantes = restaurantService.getAllByCep(cep);
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTOList(restaurantes));
    }

    @PutMapping("api/restaurantes/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurante(
            @PathVariable Long id,
            @RequestBody RestaurantDTO restaurantDTO) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + id);
        }

        Restaurant restaurant = restaurantMapper.toSource(restaurantDTO);
        restaurant.setId(id);
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTO(restaurantService.update(restaurant)));
    }

    @DeleteMapping("/restaurantes/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
