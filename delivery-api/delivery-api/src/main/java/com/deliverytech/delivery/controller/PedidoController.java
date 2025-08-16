package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.mapper.OrderMapper;
import com.deliverytech.delivery.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/pedido")
@Tag(name = "Pedidos", description = "Operações relacionadas a Pedidos")
public class PedidoController {

    private OrderService pedidoService;
    private OrderMapper orderMapper;

    @Autowired
    public PedidoController(
            OrderService pedidoService,
            OrderMapper orderMapper) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<OrderResponseDTO> createPedido(@RequestBody Order pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }

        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(pedidoService.create(pedido)));
    }

    @GetMapping("{id}")
    @Operation(summary = "Pesquisa um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido pesquisado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<OrderResponseDTO> getPedidoById(@PathVariable Long id) {
        Order pedido = pedidoService.findById(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(pedido));
    }

    @GetMapping("/cliente/{clienteId}/pedidos")
    @Operation(summary = "Pesquisa todos pedidos de um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido pesquisado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<List<OrderResponseDTO>> getAllPedidosByCliente(@PathVariable Long clienteId) {
        List<Order> pedidos = pedidoService.findByCustomerId(clienteId);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTOList(pedidos));
    }

    @PatchMapping("{id}")
    @Operation(summary = "Atualiza os dados do pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<OrderResponseDTO> updatePedido(@PathVariable Long id, @RequestBody Order pedido) {
        Order existingPedido = pedidoService.findById(id);
        if (existingPedido != null) {
            existingPedido.setStatus(pedido.getStatus());
            return ResponseEntity.ok(orderMapper.toOrderResponseDTO(pedidoService.update(existingPedido)));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("api/pedidos/{id}")
    @Operation(summary = "Deleta os dados do pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
