package com.deliverytech.delivery.service.Interfaces;

import java.util.List;

import com.deliverytech.delivery.entity.Customer;

import jakarta.validation.constraints.NotNull;

public interface ICustomerService {
    public Customer findById(Long id);

    public Customer findByEmail(String email);

    public Customer create(@NotNull(message = "Cliente não pode ser nulo") Customer cliente);

    public Customer delete(Long id);

    public Customer update(Customer customer);

    public Customer activeOrDesactiveCustomer(Long id);

    public List<Customer> findAll();

    public List<Customer> getListCustomerActive();
}
