package com.deliverytech.delivery.service;

import com.deliverytech.delivery.DTOs.Requests.CustomerDTO;
import com.deliverytech.delivery.DTOs.Response.CustomerResponseDTO;
import com.deliverytech.delivery.entity.Customer;
import com.deliverytech.delivery.mapper.CustomerMapper;
import com.deliverytech.delivery.repository.ICustomerRepository;
import com.deliverytech.delivery.service.Interfaces.ICustomerService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CustomerService implements ICustomerService {

    private ICustomerRepository clienteRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(
            ICustomerRepository clienteRepository,
            CustomerMapper customerMapper) {
        this.clienteRepository = clienteRepository;
    }

    // Busca um cliente pelo ID
    public CustomerResponseDTO findById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        Customer cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado: " + id));

        return customerMapper.toCustomerResponseDTO(cliente);
    }

    // Busca um cliente pelo email
    public CustomerResponseDTO findByEmail(String email) {

        if (email == null || email.isEmpty()) {
            throw new EntityNotFoundException("Email do cliente não pode ser nulo ou vazio");
        }

        Customer cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente não encontrado com o email: " + email);
        }

        return customerMapper.toCustomerResponseDTO(cliente);
    }

    // Criação de um novo cliente
    @Transactional
    public CustomerResponseDTO create(@NotNull(message = "Cliente não pode ser nulo") CustomerDTO cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        Customer savedCustomer = clienteRepository.save(customerMapper.toSource(cliente));
        return customerMapper.toCustomerResponseDTO(savedCustomer);
    }

    // deletar um cliente
    @Transactional
    public void delete(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        Customer cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        clienteRepository.delete(cliente);
    }

    // Atualizar um cliente
    @Transactional
    public CustomerResponseDTO update(CustomerDTO customer) {

        if (customer == null || customer.getId() == null) {
            throw new IllegalArgumentException("Cliente inválido");
        }

        if (!clienteRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException("Cliente não encontrado: " + customer.getId());
        }

        Customer updatedCustomer = customerMapper.toSource(customer);
        updatedCustomer.setId(customer.getId());
        return customerMapper.toCustomerResponseDTO(clienteRepository.save(updatedCustomer));
    }

    // Ativar ou desativar um cliente
    public CustomerResponseDTO activeOrDesactiveCustomer(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        Customer cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        cliente.setActive(!cliente.isActive());

        return customerMapper.toCustomerResponseDTO(clienteRepository.save(cliente));
    }

    public List<CustomerResponseDTO> findAll() {
        return customerMapper.toCustomerResponseDTOList(clienteRepository.findAll().stream().toList());
    }

    // Lista todos os clientes ativos
    public List<CustomerResponseDTO> getListCustomerActive() {
        return customerMapper.toCustomerResponseDTOList(clienteRepository.findByActive(true));
    }
}