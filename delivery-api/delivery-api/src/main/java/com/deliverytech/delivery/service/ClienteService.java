package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Cliente;
import com.deliverytech.delivery.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Cliente com email já cadastrado: " + cliente.getEmail());
        }

        return clienteRepository.save(cliente);
    }

    public Cliente findById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));
    }

    public Cliente update(Cliente cliente) {

        if (cliente.getId() == null || cliente.getId() <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + cliente.getId());
        }

        if (!clienteRepository.existsById(cliente.getId())) {
            throw new IllegalArgumentException("Cliente não encontrado: " + cliente.getId());
        }

        return clienteRepository.save(cliente);
    }

    public void InactivateClient(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido: " + id);
        }

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}