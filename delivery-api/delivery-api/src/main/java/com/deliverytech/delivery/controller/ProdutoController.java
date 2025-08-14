package com.deliverytech.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.entity.Product;
import com.deliverytech.delivery.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProdutoController {

    private ProductService produtoService;

    @Autowired
    public ProdutoController(ProductService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produto")
    public Product createProduto(@RequestBody Product produto) {
        return produtoService.create(produto);
    }

    @PutMapping("/produto/{id}")
    public Product updateProduto(@PathVariable Long id, @RequestBody Product produtos) {
        produtos.setId(id);
        return produtoService.update(produtos);
    }

}
