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

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("api/restaurantes")
@Tag(name = "Restaurantes", description = "Operações relacionadas a restaurantes")
public class RestauranteController {

    private RestaurantService restaurantService;
    private RestaurantMapper restaurantMapper;

    @Autowired
    public RestauranteController(
            RestaurantService restaurantService,
            RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    // Cria um restaurante
    @PostMapping
    @Operation(summary = "Cria um novo restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<RestaurantResponseDTO> createRestaurante(
            @NotNull @RequestBody RestaurantDTO restauranteDTO) {

        Restaurant restaurant = restaurantMapper.toSource(restauranteDTO);
        Restaurant restauranteCreate = restaurantService.create(restaurant);

        if (restauranteCreate == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurantMapper.toProductResponseDTO(restauranteCreate));
    }

    @GetMapping("{id}")
    @PostMapping
    @Operation(summary = "Obtém um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante obtido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<RestaurantResponseDTO> getRestaurante(
            @PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + id);
        }

        Restaurant restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTO(restaurant));
    }

    @GetMapping
    @Operation(summary = "Obtém uma lista de restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Requisição inválida")
    })
    public ResponseEntity<List<RestaurantResponseDTO>> getAll() {
        List<Restaurant> restaurantes = restaurantService.findAll();
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTOList(restaurantes));
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Obtém uma lista de restaurantes por categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtigda com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Requisição inválida")
    })
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantesByCategoria(
            @PathVariable String categoria) {
        List<Restaurant> restaurantes = restaurantService.getAllByCategory(categoria);
        return ResponseEntity.ok(restaurantMapper.toProductResponseDTOList(restaurantes));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurando atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Requisição inválida")
    })
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Restaurante deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
    })
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
