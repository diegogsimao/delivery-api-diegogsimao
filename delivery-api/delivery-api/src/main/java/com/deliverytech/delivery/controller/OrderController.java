package com.deliverytech.delivery.controller;

import java.math.BigDecimal;
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

import com.deliverytech.delivery.DTOs.Requests.OrderDTO;
import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.service.Interfaces.IOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/pedido")
@Tag(name = "Pedidos", description = "Operações relacionadas a Pedidos")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    public OrderController(
            IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "409", description = "Pedido já existe")
    })
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestBody OrderDTO pedido) {

        return ResponseEntity.ok(orderService.createOrder(pedido));
    }

    @GetMapping("{id}")
    @Operation(summary = "Pesquisa um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido pesquisado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<OrderResponseDTO> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PatchMapping("pedidos/{id}/status")
    @Operation(summary = "Atualiza os dados do pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<OrderResponseDTO> updatePedidoStatus(
            @PathVariable Long id,
            @RequestBody OrderDTO orderDTO) {

        if (orderDTO == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }

        // return ResponseEntity.ok(orderService.updateOrderStatus(id, orderDTO));
        return null;

    }

    @DeleteMapping("pedidos/{id}")
    @Operation(summary = "Deleta os dados do pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
