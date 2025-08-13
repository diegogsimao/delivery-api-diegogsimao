package com.deliverytech.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deliverytech.delivery.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.active = true")
    List<Customer> findByActiveTrue();

    Customer findByNameContainingIgnoreCase(String name);

    boolean existsByEmail(String email);

    List<Customer> findByActive(boolean active);
}