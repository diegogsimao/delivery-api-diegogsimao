package com.deliverytech.delivery.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.entity.OrderItem;
import com.deliverytech.delivery.entity.enums.OrderStatus;
import com.deliverytech.delivery.repository.IOrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private IOrderRepository pedidoRepository;

    @Autowired
    public OrderService(IOrderRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    // Cria um pedido
    public Order create(Order pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        return pedidoRepository.save(pedido);
    }

    @Transactional
    // Atualiza um pedido
    public Order update(Order pedido) {
        if (pedido == null || pedido.getId() == null) {
            throw new IllegalArgumentException("Pedido ou ID do pedido não pode ser nulo");
        }
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        Order pedido = findById(id);
        if (pedido != null) {
            pedidoRepository.delete(pedido);
        } else {
            throw new IllegalArgumentException("Pedido não encontrado com o ID: " + id);
        }
    }

    // Busca um pedido por ID
    public Order findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com o ID: " + id));
    }

    // Busca todos os pedidos por ID do clientes
    public List<Order> findByCustomerId(Long customerId) {
        if (customerId == null || customerId <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido");
        }
        return pedidoRepository.findByCustomerId(customerId);
    }

    // Atualiza o status do pedido
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = findById(id);
        if (order == null) {
            throw new IllegalArgumentException("Pedido não encontrado com o ID: " + id);
        }
        order.setStatus(status);
        return pedidoRepository.save(order);
    }

    public Order calculateTotalOrder(List<OrderItem> itens) {

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula ou vazia");
        }

        BigDecimal total = itens.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setValorTotal(total);
        return order;
    }

    // Cancela um pedido
    public Order cancelOrder(Long id) {
        Order order = findById(id);
        if (order == null) {
            throw new IllegalArgumentException("Pedido não encontrado com o ID: " + id);
        }

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new IllegalArgumentException("Pedido já foi entregue e não pode ser cancelado");
        }

        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalArgumentException("Pedido já foi cancelado");
        }

        order.setStatus(OrderStatus.CANCELLED);
        return pedidoRepository.save(order);
    }
}
