package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.repository.Interfaces.IProductRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ProductRepositoryImpl implements IProductRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;
}
