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
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.entity.OrderItem;
import com.deliverytech.delivery.mapper.OrderMapper;
import com.deliverytech.delivery.service.OrderServiceImpl;
import com.deliverytech.delivery.service.Interfaces.IOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/pedido")
@Tag(name = "Pedidos", description = "Operações relacionadas a Pedidos")
public class OrderController {

    private IOrderService orderService;
    private OrderMapper orderMapper;

    @Autowired
    public OrderController(
            IOrderService orderService,
            OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<OrderResponseDTO> createPedido(
            @RequestBody OrderDTO pedido) {

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }

        return ResponseEntity.ok(orderService.create(pedido));
    }

    @GetMapping("{id}")
    @Operation(summary = "Pesquisa um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido pesquisado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<OrderResponseDTO> getPedidoById(@PathVariable Long id) {
        Order pedido = orderService.findById(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(pedido));
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
            @RequestBody Order pedido) {
        Order existingPedido = orderService.findById(id);
        if (existingPedido != null) {
            existingPedido.setStatus(pedido.getStatus());
            return ResponseEntity.ok(orderMapper.toOrderResponseDTO(orderService.update(existingPedido)));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("pedidos/{id}")
    @Operation(summary = "Deleta os dados do pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}/pedidos")
    @Operation(summary = "Pesquisa todos pedidos de um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido pesquisado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<List<OrderResponseDTO>> getAllPedidosByCliente(@PathVariable Long clienteId) {
        List<Order> pedidos = orderService.findByCustomerId(clienteId);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTOList(pedidos));
    }

    @GetMapping("/api/restaurantes/{restauranteId}/pedidos")
    @Operation(summary = "Pesquisa todos pedidos de um restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido pesquisado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<List<OrderResponseDTO>> getAllPedidosByRestaurante(@PathVariable Long restauranteId) {
        List<Order> pedidos = orderService.findByRestaurantId(restauranteId);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTOList(pedidos));
    }

    @GetMapping("pedidos/calcular")
    @Operation(summary = "Calcula o valor total de um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Valor total calculado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<BigDecimal> calcularValorTotal(
            @RequestBody List<OrderItem> itens) {

        try {
            Order order = orderService.calculateTotalOrder(itens);
            return ResponseEntity.ok(order.getValorTotal());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
