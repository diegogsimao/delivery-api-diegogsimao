package com.deliverytech.delivery.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.DTOs.Requests.OrderDTO;
import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.mapper.OrderMapper;
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.entity.OrderItem;
import com.deliverytech.delivery.entity.enums.OrderStatus;
import com.deliverytech.delivery.repository.IOrderRepository;
import com.deliverytech.delivery.service.Interfaces.IOrderService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    private IOrderRepository pedidoRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(
            IOrderRepository pedidoRepository,
            OrderMapper orderMapper) {

        this.pedidoRepository = pedidoRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    // Cria um pedido
    public OrderResponseDTO create(OrderDTO pedido) {

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }

        // Mapeamento da DTO para Entity
        Order order = orderMapper.toSource(pedido);

        // Retorno já mapeia de Entity para DTO
        return orderMapper.toOrderResponseDTO(pedidoRepository.save(order));
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

    @Transactional
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

    public List<Order> findByRestaurantId(Long restauranteId) {
        if (restauranteId == null || restauranteId <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido");
        }
        return pedidoRepository.findByRestaurantId(restauranteId);
    }
}
