package com.deliverytech.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
}
