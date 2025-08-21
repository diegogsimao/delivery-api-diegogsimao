package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Response.ProductResponseDTO;
import com.deliverytech.delivery.entity.Product;
import com.deliverytech.delivery.mapper.ProductMapper;
import com.deliverytech.delivery.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/produto")
@Tag(name = "Produtos", description = "Operações relacionadas a Produtos")
public class ProductController {

    private ProductService produtoService;
    private ProductMapper productMapper;

    @Autowired
    public ProductController(
            ProductService produtoService,
            ProductMapper productMapper) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo Produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ProductResponseDTO createProduto(@RequestBody Product produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        return productMapper.toProductResponseDTO(produtoService.create(produto));
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza um Produto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para restaurante pesquisado")
    })
    public ResponseEntity<ProductResponseDTO> updateProduto(
            @PathVariable Long id,
            @RequestBody Product produtos) {

        if (produtos == null || produtos.getId() == null) {
            throw new IllegalArgumentException("Produto ou ID não pode ser nulo");
        }
        produtos.setId(id);
        return ResponseEntity.ok(productMapper.toProductResponseDTO(produtoService.update(produtos)));
    }

    @PatchMapping("/{id}/disponibilidade")
    @Operation(summary = "Atualiza a disponibilidade de um Produto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para restaurante pesquisado")
    })
    public ResponseEntity<ProductResponseDTO> updateProdutoDisponibilidade(
            @PathVariable Long id,
            @RequestBody boolean disponibilidade) {

        return ResponseEntity
                .ok(productMapper.toProductResponseDTO(produtoService.updateAvailable(id, disponibilidade)));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remove um Produto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para restaurante pesquisado")
    })
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurantes/{restauranteId}/produtos")
    @Operation(summary = "Cria um novo Produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para restaurante pesquisado")
    })
    public ResponseEntity<List<ProductResponseDTO>> getProdutosByRestaurante(
            @PathVariable Long restauranteId) {

        List<Product> produtos = produtoService.findByRestauranteId(restauranteId);
        return ResponseEntity.ok(productMapper.toProductResponseDTOList(produtos));
    }

    @GetMapping("categoria/{categoria}")
    @Operation(summary = "Pesquisa um produto por categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para categoria pesquisada")
    })
    public ResponseEntity<List<ProductResponseDTO>> getProdutosByCategoria(
            @PathVariable String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria inválida");
        }

        return ResponseEntity
                .ok(productMapper.toProductResponseDTOList(produtoService.findAllByCategoryName(categoria)));
    }

    @GetMapping("/api/produtos/buscar?nome={nome}")
    @Operation(summary = "Pesquisa um produto por nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pesquisa realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado para nome pesquisado")
    })
    public ResponseEntity<List<ProductResponseDTO>> getProdutosByNome(
            @PathVariable String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        return ResponseEntity
                .ok(productMapper.toProductResponseDTOList(produtoService.findByName(nome)));
    }
}
