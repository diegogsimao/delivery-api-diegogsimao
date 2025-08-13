package com.deliverytech.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.repository.IOrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private IOrderRepository pedidoRepository;

    public Order create(Order pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        return pedidoRepository.save(pedido);
    }

    public Order update(Order pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        return pedidoRepository.save(pedido);
    }

    public Order delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
        return null; // ou retornar um objeto de confirmação, se necessário
    }
}
