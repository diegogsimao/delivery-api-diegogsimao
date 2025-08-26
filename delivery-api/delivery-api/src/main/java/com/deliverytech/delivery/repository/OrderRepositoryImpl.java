package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.DTOs.Requests.OrderDTO;
import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.repository.Interfaces.IOrderRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class OrderRepositoryImpl implements IOrderRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;
}
