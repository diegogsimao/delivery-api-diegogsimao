package com.deliverytech.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Response.ProductResponseDTO;
import com.deliverytech.delivery.entity.Product;
import com.deliverytech.delivery.mapper.ProductMapper;
import com.deliverytech.delivery.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProdutoController {

    private ProductService produtoService;
    private ProductMapper productMapper;

    @Autowired
    public ProdutoController(
            ProductService produtoService,
            ProductMapper productMapper) {
        this.produtoService = produtoService;
    }

    @PostMapping("api/produto")
    public ProductResponseDTO createProduto(@RequestBody Product produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        return productMapper.toProductResponseDTO(produtoService.create(produto));
    }

    @GetMapping("api/restaurantes/{restauranteId}/produtos")
    public ResponseEntity<List<ProductResponseDTO>> getProdutosByRestaurante(
            @PathVariable Long restauranteId) {

        List<Product> produtos = produtoService.findByRestauranteId(restauranteId);
        return ResponseEntity.ok(productMapper.toProductResponseDTOList(produtos));
    }

    @PutMapping("api/produto/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduto(
            @PathVariable Long id,
            @RequestBody Product produtos) {

        if (produtos == null || produtos.getId() == null) {
            throw new IllegalArgumentException("Produto ou ID não pode ser nulo");
        }
        produtos.setId(id);
        return ResponseEntity.ok(productMapper.toProductResponseDTO(produtoService.update(produtos)));
    }

    @PatchMapping("api/produtos/{id}/disponibilidade")
    public ResponseEntity<ProductResponseDTO> updateProdutoDisponibilidade(
            @PathVariable Long id,
            @RequestBody boolean disponibilidade) {

        return ResponseEntity
                .ok(productMapper.toProductResponseDTO(produtoService.updateAvailable(id, disponibilidade)));
    }

    @GetMapping("api/produtos/categoria/{categoria}")
    public ResponseEntity<List<ProductResponseDTO>> getProdutosByCategoria(
            @PathVariable String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("Categoria inválida");
        }

        return ResponseEntity
                .ok(productMapper.toProductResponseDTOList(produtoService.findAllByCategoryName(categoria)));
    }
}
