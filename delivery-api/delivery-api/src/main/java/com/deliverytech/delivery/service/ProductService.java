package com.deliverytech.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Product;
import com.deliverytech.delivery.repository.IProdutoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private IProdutoRepository produtoRepository;

    public Product create(Product produto) {
        return produtoRepository.save(produto);
    }

    public Product update(Product produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
