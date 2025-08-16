package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Requests.CustomerDTO;
import com.deliverytech.delivery.DTOs.Response.CustomerResponseDTO;
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
    public ResponseEntity<CustomerResponseDTO> createCliente(@RequestBody CustomerDTO clienteDTO) {
        Customer entity = customerMapper.toSource(clienteDTO);
        return ResponseEntity.ok(customerMapper.toCustomerResponseDTO(clienteService.create(entity)));
    }

    @GetMapping("api/clientes/{id}")
    public ResponseEntity<CustomerResponseDTO> getCliente(@PathVariable Long id) {
        Customer entity = clienteService.findById(id);
        return ResponseEntity.ok(customerMapper.toCustomerResponseDTO(entity));
    }

    @GetMapping("api/clientes")
    public ResponseEntity<List<CustomerResponseDTO>> getAllClientes() {
        List<Customer> entities = clienteService.findAll();
        return ResponseEntity.ok(customerMapper.toCustomerResponseDTOList(entities));
    }

    @GetMapping("api/clientes/email/{email}")
    public ResponseEntity<CustomerResponseDTO> getClienteByEmail(@PathVariable String email) {
        Customer entity = clienteService.findByEmail(email);
        return ResponseEntity.ok(customerMapper.toCustomerResponseDTO(entity));
    }

    @PutMapping("api/clientes/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCliente(@PathVariable Long id,
            @RequestBody CustomerDTO clienteDTO) {
        Customer entity = customerMapper.toSource(clienteDTO);
        entity.setId(id);
        return ResponseEntity.ok(customerMapper.toCustomerResponseDTO(clienteService.update(entity)));
    }

    @PatchMapping("api/clientes/{id}")
    public ResponseEntity<CustomerResponseDTO> partialUpdateCliente(@PathVariable Long id,
            @RequestBody CustomerDTO clienteDTO) {
        Customer entity = customerMapper.toSource(clienteDTO);
        entity.setId(id);
        return ResponseEntity.ok(customerMapper.toCustomerResponseDTO(clienteService.update(entity)));
    }
}
