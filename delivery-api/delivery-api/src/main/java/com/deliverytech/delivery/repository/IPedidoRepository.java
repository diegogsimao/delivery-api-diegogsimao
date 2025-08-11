package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverytech.delivery.entity.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

}
