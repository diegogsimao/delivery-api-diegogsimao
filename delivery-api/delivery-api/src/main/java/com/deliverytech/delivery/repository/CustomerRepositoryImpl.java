package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.repository.Interfaces.ICustomerRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomerRepositoryImpl implements ICustomerRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;
}
