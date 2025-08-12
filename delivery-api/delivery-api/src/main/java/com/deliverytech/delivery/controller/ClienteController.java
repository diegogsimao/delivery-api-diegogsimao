package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.entity.Customer;
import com.deliverytech.delivery.service.CustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ClienteController {

    @Autowired
    private CustomerService clienteService;

    @PostMapping("/clientes")
    public Customer createCliente(@RequestBody Customer cliente) {
        return clienteService.create(cliente);
    }

    @GetMapping("/clientes")
    public List<Customer> getAllClientes() {
        return clienteService.getAll();
    }

    @DeleteMapping("/clientes/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.delete(id);
    }
}