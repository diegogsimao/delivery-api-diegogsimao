package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Customer;
import com.deliverytech.delivery.repository.ICustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CustomerService {

    private ICustomerRepository clienteRepository;

    @Autowired
    public CustomerService(
            ICustomerRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Busca um cliente pelo ID
    public Customer findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado: " + id));
    }

    // Busca um cliente pelo email
    public Customer findByEmail(String email) {

        if (email == null || email.isEmpty()) {
            throw new EntityNotFoundException("Email do cliente não pode ser nulo ou vazio");
        }
        return clienteRepository.findByEmail(email);
    }

    // Criação de um novo cliente
    @Transactional
    public Customer create(@NotNull(message = "Cliente não pode ser nulo") Customer cliente) {

        return clienteRepository.save(cliente);
    }

    // deletar um cliente
    @Transactional
    public Customer delete(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        Customer cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        clienteRepository.delete(cliente);
        return cliente;
    }

    // Atualizar um cliente
    @Transactional
    public Customer update(Customer customer) {
        if (customer == null || customer.getId() == null) {
            throw new IllegalArgumentException("Cliente inválido");
        }

        if (!clienteRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException("Cliente não encontrado: " + customer.getId());
        }

        return clienteRepository.save(customer);
    }

    // Ativar ou desativar um cliente
    public Customer activeOrDesactiveCustomer(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        Customer cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        cliente.setActive(!cliente.isActive());
        return clienteRepository.save(cliente);
    }

    public List<Customer> findAll() {
        return clienteRepository.findAll();
    }

    // Lista todos os clientes ativos
    public List<Customer> getListCustomerActive() {
        return clienteRepository.findByActive(true);
    }
}