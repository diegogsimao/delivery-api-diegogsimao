package com.deliverytech.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class PedidoController {
    @Autowired
    private OrderService pedidoService;

    @PostMapping("/pedido")
    public Order createPedido(@RequestBody Order pedido) {
        return pedidoService.create(pedido);
    }

    @PutMapping("/pedido/{id}")
    public Order updatePedido(@PathVariable Long id, @RequestBody Order pedido) {
        pedido.setId(id);
        return pedidoService.update(pedido);
    }

    @DeleteMapping("/pedido/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
    }

}
