package com.deliverytech.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido Create(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        return pedidoRepository.save(pedido);
    }

    public Pedido Update(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        return pedidoRepository.save(pedido);
    }

    public void CalcularValores(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        // Lógica para calcular os valores do pedido
    }

    public void AlterarStatusPedido(Pedido pedido, String status) {
        if (pedido == null || status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Pedido ou status inválidos");
        }
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
    }

    public void ValidarPedido(Pedido pedido) {

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        // Lógica para validar o pedido
    }
}
