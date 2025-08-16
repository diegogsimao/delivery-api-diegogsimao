package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.mapper.OrderMapper;
import com.deliverytech.delivery.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PedidoController {

    private OrderService pedidoService;
    private OrderMapper orderMapper;

    @Autowired
    public PedidoController(
            OrderService pedidoService,
            OrderMapper orderMapper) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedido")
    public ResponseEntity<OrderResponseDTO> createPedido(@RequestBody Order pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }

        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(pedidoService.create(pedido)));
    }

    @GetMapping("api/pedidos/{id}")
    public ResponseEntity<OrderResponseDTO> getPedidoById(@PathVariable Long id) {
        Order pedido = pedidoService.findById(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderMapper.toOrderResponseDTO(pedido));
    }

    @GetMapping("api/pedidos/cliente/{clienteId}/pedidos")
    public ResponseEntity<List<OrderResponseDTO>> getAllPedidosByCliente(@PathVariable Long clienteId) {
        List<Order> pedidos = pedidoService.findByCustomerId(clienteId);
        return ResponseEntity.ok(orderMapper.toOrderResponseDTOList(pedidos));
    }

    @PatchMapping("api/pedidos/{id}")
    public ResponseEntity<OrderResponseDTO> updatePedido(@PathVariable Long id, @RequestBody Order pedido) {
        Order existingPedido = pedidoService.findById(id);
        if (existingPedido != null) {
            existingPedido.setStatus(pedido.getStatus());
            return ResponseEntity.ok(orderMapper.toOrderResponseDTO(pedidoService.update(existingPedido)));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("api/pedidos/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
