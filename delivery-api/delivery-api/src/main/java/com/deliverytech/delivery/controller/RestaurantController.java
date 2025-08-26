
package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery.DTOs.Requests.RestaurantDTO;
import com.deliverytech.delivery.DTOs.Response.RestaurantResponseDTO;
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
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(
            RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
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

        return ResponseEntity.ok(restaurantService.create(restauranteDTO));
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantesAtivos() {

        return ResponseEntity.ok(restaurantService.findByActive());
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

        return ResponseEntity.ok(restaurantService.getAllByCategory(categoria));
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtém um restaurante por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante obtido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<RestaurantResponseDTO> getRestaurante(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + id);
        }
        return ResponseEntity.ok(restaurantService.findById(id));
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

        return ResponseEntity.ok(restaurantService.update(restaurantDTO));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Ativa um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Restaurante ativado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<RestaurantResponseDTO> updateActive(
            @PathVariable Long id,
            @RequestBody RestaurantDTO restaurantDTO) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido: " + id);
        }
        // Devolvendo a resposta para o usuário
        return ResponseEntity.ok(restaurantService.updateActive(restaurantDTO));
    }

    @GetMapping("/proximos/{cep}")
    @Operation(summary = "Busca restaurantes com CEPs próximos")
    public ResponseEntity<List<RestaurantResponseDTO>> getRestaurantesByCep(
            @PathVariable String cep) {

        return ResponseEntity.ok(restaurantService.findByCep(cep));
    }

    @GetMapping("/{id}/taxa-entrega/{cep}")

    @Operation(summary = "Busca a taxa de entrega de um restaurante")
    public ResponseEntity<Double> getTaxaEntrega(

            @PathVariable Long id,

            @PathVariable String cep) {

        Double taxaEntrega = restaurantService.calcularTaxaEntrega(id, cep);
        return ResponseEntity.ok(taxaEntrega);
    }

}
