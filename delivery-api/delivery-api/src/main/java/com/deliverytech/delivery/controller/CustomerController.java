package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Requests.CustomerDTO;
import com.deliverytech.delivery.DTOs.Response.CustomerResponseDTO;
import com.deliverytech.delivery.Helpers.ApiResponseCustom;
import com.deliverytech.delivery.entity.Customer;
import com.deliverytech.delivery.mapper.CustomerMapper;
import com.deliverytech.delivery.service.CustomerService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/cliente")
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

        @PostMapping
        @Operation(summary = "Cria um novo cliente")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
        })
        public ResponseEntity<ApiResponseCustom<CustomerResponseDTO>> createCliente(
                        @RequestBody CustomerDTO clienteDTO) {

                Customer entity = customerMapper.toSource(clienteDTO);
                return ResponseEntity.ok(
                                new ApiResponseCustom<>(true,
                                                customerMapper.toCustomerResponseDTO(clienteService.create(entity)),
                                                null));
        }

        @GetMapping("{id}")
        @Operation(summary = "Pesquisa um cliente")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
        })
        public ResponseEntity<ApiResponseCustom<CustomerResponseDTO>> getCliente(
                        @PathVariable Long id) {
                Customer entity = clienteService.findById(id);
                if (entity == null) {
                        return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(
                                new ApiResponseCustom<>(true,
                                                customerMapper.toCustomerResponseDTO(entity),
                                                null));
        }

        @GetMapping("api/clientes")
        @Operation(summary = "Pesquisa todos os clientes")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Clientes não encontrados")
        })
        public ResponseEntity<List<CustomerResponseDTO>> getAllClientes(
                        @PageableDefault(size = 10) Pageable pageable) {

                List<Customer> entities = clienteService.findAll();
                return ResponseEntity.ok(customerMapper.toCustomerResponseDTOList(entities));
        }

        @GetMapping("api/clientes/email/{email}")
        @Operation(summary = "Pesquisa cliente por email")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Clientes não encontrados")
        })
        public ResponseEntity<CustomerResponseDTO> getClienteByEmail(@PathVariable String email) {
                Customer entity = clienteService.findByEmail(email);
                if (entity == null) {
                        return ResponseEntity.notFound().build();
                }
                return ResponseEntity.ok(customerMapper.toCustomerResponseDTO(entity));
        }

        @PutMapping("api/clientes/{id}")
        @Operation(summary = "Atualiza os dados do  cliente")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Cliente não encontrados")
        })
        public ResponseEntity<CustomerResponseDTO> updateCliente(@PathVariable Long id,
                        @RequestBody CustomerDTO clienteDTO) {
                Customer entity = customerMapper.toSource(clienteDTO);
                entity.setId(id);
                return ResponseEntity.ok(customerMapper.toCustomerResponseDTO(clienteService.update(entity)));
        }

        @PatchMapping("api/clientes/{id}")
        @Operation(summary = "Atualiza os dados do  cliente")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Clientes encontrados com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
                        @ApiResponse(responseCode = "404", description = "Cliente não encontrados")
        })
        public ResponseEntity<CustomerResponseDTO> partialUpdateCliente(@PathVariable Long id,
                        @RequestBody CustomerDTO clienteDTO) {
                Customer entity = customerMapper.toSource(clienteDTO);
                entity.setId(id);
                return ResponseEntity.ok(customerMapper.toCustomerResponseDTO(clienteService.update(entity)));
        }
}
