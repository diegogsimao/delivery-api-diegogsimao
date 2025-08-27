package com.deliverytech.delivery.repository;

import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.repository.Interfaces.IRestaurantRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RestaurantRepositoryImpl implements IRestaurantRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    
}
