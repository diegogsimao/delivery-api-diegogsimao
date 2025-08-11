package com.deliverytech.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.deliverytech.delivery.entity.Produto;
import com.deliverytech.delivery.service.ProdutoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/produto")
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoService.create(produto);
    }

    @PutMapping("/produto/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produtos) {
        produtos.setId(id);
        return produtoService.update(produtos);
    }

    @DeleteMapping("/produto/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoService.delete(id);
    }
}
