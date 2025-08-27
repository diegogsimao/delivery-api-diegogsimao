package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.repository.Interfaces.IRestaurantRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class RestaurantRepositoryImpl implements IRestaurantRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;
}
