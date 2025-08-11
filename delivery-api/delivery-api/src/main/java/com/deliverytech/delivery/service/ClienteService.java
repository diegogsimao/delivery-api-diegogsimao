package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Cliente;
import com.deliverytech.delivery.repository.IClienteRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private IClienteRepository clienteRepository;

    // Listar todos os clientes
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    // Criação de um novo cliente
    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // deletar um cliente
    public Cliente delete(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        clienteRepository.delete(cliente);
        return cliente;
    }

}