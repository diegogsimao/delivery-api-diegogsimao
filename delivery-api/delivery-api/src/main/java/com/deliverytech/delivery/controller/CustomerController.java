package com.deliverytech.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.CustomerDTO;
import com.deliverytech.delivery.entity.Customer;
import com.deliverytech.delivery.mapper.CustomerMapper;
import com.deliverytech.delivery.service.CustomerService;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Clientes", description = "APIs para gerenciamento de clientes")
public class CustomerController {

    private CustomerService clienteService;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerController(
            CustomerService clienteService,
            CustomerMapper customerMapper) {

        this.clienteService = clienteService;
        this.customerMapper = customerMapper;
    }

    @PostMapping("api/clientes")
    public Customer createCliente(@RequestBody CustomerDTO clienteDTO) {
        Customer entity = customerMapper.toSource(clienteDTO);
        return clienteService.create(entity);
    }
}