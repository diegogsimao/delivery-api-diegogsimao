package com.deliverytech.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Customer;

@Repository
public interface IClienteRepository extends JpaRepository<Customer, Long> {

}