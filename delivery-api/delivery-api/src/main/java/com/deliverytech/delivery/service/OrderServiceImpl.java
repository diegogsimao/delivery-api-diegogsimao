package com.deliverytech.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.DTOs.Requests.OrderDTO;
import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.mapper.OrderMapper;
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.entity.enums.OrderStatus;
import com.deliverytech.delivery.exceptions.EntityNotFoundException;
import com.deliverytech.delivery.repository.Interfaces.IOrderRepository;
import com.deliverytech.delivery.service.Interfaces.IOrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

    private IOrderRepository orderRepository;
    private OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(
            IOrderRepository orderRepository,
            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderDTO orderDTO) {

        if (orderDTO == null) {
            throw new EntityNotFoundException("Order cannot be null");
        }

        // Mapeamento da DTO para Entity
        Order order = orderMapper.toSource(orderDTO);

        // Retorno já mapeia de Entity para DTO
        return orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }

    @Transactional
    // Atualiza um pedido
    public OrderResponseDTO update(OrderDTO order) {

        if (order == null || order.getId() == null) {
            throw new IllegalArgumentException("Pedido ou ID do pedido não pode ser nulo");
        }

        // Mapeamento da DTO para Entity
        Order existingOrder = orderMapper.toSource(order);
        return orderMapper.toOrderResponseDTO(orderRepository.save(existingOrder));
    }

    @Transactional
    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }

        Order order = orderRepository.findById(id).orElse(null);

        if (order != null) {
            orderRepository.delete(order);
        } else {
            throw new IllegalArgumentException("Pedido não encontrado com o ID: " + id);
        }
    }

    // Busca um pedido por ID
    public OrderResponseDTO findById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return orderRepository.findById(id)
                .map(orderMapper::toOrderResponseDTO)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com o ID: " + id));
    }

    @Transactional
    // Atualiza o status do pedido
    public OrderResponseDTO updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new IllegalArgumentException("Pedido não encontrado com o ID: " + id);
        }
        order.setStatus(status);
        return orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }

    // Cancela um pedido
    @Transactional
    public OrderResponseDTO cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
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
        return orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }

}
