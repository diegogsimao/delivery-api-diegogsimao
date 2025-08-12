package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverytech.delivery.entity.Order;

public interface IPedidoRepository extends JpaRepository<Order, Long> {

}
