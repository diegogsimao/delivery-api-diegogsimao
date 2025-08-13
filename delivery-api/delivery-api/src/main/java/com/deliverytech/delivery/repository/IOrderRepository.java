package com.deliverytech.delivery.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverytech.delivery.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {

    // public Order findByCustomerId(Long customerId);

    // public List<Order> findByStatus(StatusPedido status)

    // public List<Order> findTop10ByOrderByDataPedidoDesc();

    // public List<Order> findByDataPedidoBetween(LocalDateTime inicio,
    // LocalDateTime fim);
}