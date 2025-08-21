package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.entity.Order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/Relatorio")
@Tag(name = "Relatórios", description = "APIs para gerenciamento de relatórios")
public class RelatorioController {

    @GetMapping("/relatorios/vendas-por-restaurante")
    @Operation(summary = "Relatório de vendas por restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<List<OrderResponseDTO>> getVendasPorRestaurante() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/relatorios/produtos-mais-vendidos")
    @Operation(summary = "Relatório de produtos mais vendidos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<List<OrderResponseDTO>> getProdutosMaisVendidos() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/relatorios/clientes-ativos")
    @Operation(summary = "Relatório de clientes ativo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<List<OrderResponseDTO>> getClientesAtivos() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/relatorios/pedidos-por-periodo")
    @Operation(summary = "Relatório de pedidos por período")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<List<OrderResponseDTO>> getPedidosPorPeriodo() {
        return ResponseEntity.notFound().build();
    }

}
