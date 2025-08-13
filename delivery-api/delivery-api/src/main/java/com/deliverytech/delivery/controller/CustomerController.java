package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.CustomerDTO;
import com.deliverytech.delivery.entity.Customer;
import com.deliverytech.delivery.mapper.CustomerMapper;
import com.deliverytech.delivery.service.CustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
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

    @PostMapping("/clientes")
    public Customer createCliente(@RequestBody CustomerDTO clienteDTO) {
        Customer entity = customerMapper.toSource(clienteDTO);
        return clienteService.create(entity);
    }

    @GetMapping("/clientes")
    public List<CustomerDTO> getAllClientes() {
        return clienteService.getAll();
    }

    @DeleteMapping("/clientes/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
    }
}