package com.deliverytech.delivery.service;

import com.deliverytech.delivery.DTOs.CustomerDTO;
import com.deliverytech.delivery.entity.Customer;
import com.deliverytech.delivery.mapper.CustomerMapper;
import com.deliverytech.delivery.repository.ICustomerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {

    private ICustomerRepository clienteRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(
            ICustomerRepository clienteRepository,
            CustomerMapper customerMapper) {
        this.clienteRepository = clienteRepository;
        this.customerMapper = customerMapper;
    }

    // Listar todos os clientes
    public List<CustomerDTO> getAll() {
        return clienteRepository.findAll()
                .stream()
                .map(customerMapper::toTarget)
                .toList();
    }

    // Criação de um novo cliente
    public Customer create(Customer cliente) {
        return clienteRepository.save(cliente);
    }

    // deletar um cliente
    public Customer delete(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        Customer cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        clienteRepository.delete(cliente);
        return cliente;
    }

}