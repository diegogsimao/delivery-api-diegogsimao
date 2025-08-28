package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.repository.Interfaces.IOrderRepositoryImpl;
import com.deliverytech.delivery.service.Interfaces.IOrderService;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, IOrderRepositoryImpl {

}