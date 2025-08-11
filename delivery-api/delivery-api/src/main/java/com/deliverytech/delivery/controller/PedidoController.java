package com.deliverytech.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.service.PedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/pedido")
    public Pedido createPedido(@RequestBody Pedido pedido) {
        return pedidoService.create(pedido);
    }

    @PutMapping("/pedido/{id}")
    public Pedido updatePedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return pedidoService.update(pedido);
    }

    @DeleteMapping("/pedido/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
    }

}
