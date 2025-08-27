package com.deliverytech.delivery.service.Interfaces;

import java.util.List;

import com.deliverytech.delivery.DTOs.Requests.CustomerDTO;
import com.deliverytech.delivery.DTOs.Response.CustomerResponseDTO;

import jakarta.validation.constraints.NotNull;

public interface ICustomerService {
    public CustomerResponseDTO findById(Long id);

    public CustomerResponseDTO findByEmail(String email);

    public CustomerResponseDTO create(@NotNull(message = "Cliente não pode ser nulo") CustomerDTO cliente);

    public void delete(Long id);

    public CustomerResponseDTO update(CustomerDTO customer);

    public CustomerResponseDTO activeOrDesactiveCustomer(Long id);

    public List<CustomerResponseDTO> findAll();

    public List<CustomerResponseDTO> getListCustomerActive();
}
