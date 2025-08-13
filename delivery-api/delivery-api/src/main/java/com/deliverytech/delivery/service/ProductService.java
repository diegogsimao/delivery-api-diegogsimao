package com.deliverytech.delivery.service;

import java.util.List;

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

    // Cria um produto
    public Product create(Product produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }

        return produtoRepository.save(produto);
    }

    // Busca todos os produtos ativos por ID do restaurante
    public List<Product> findAllByRestaurantActiveById(long restaurantId) {
        if (restaurantId <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido");
        }
        return produtoRepository.findAllByRestaurantActiveById(restaurantId);
    }

    // Busca um produto por ID
    public Product findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return produtoRepository.findById(id).orElse(null);
    }

    // Atualiza um produto
    public Product update(Product produto) {
        if (produto == null || produto.getId() == null || produto.getId() <= 0) {
            throw new IllegalArgumentException("Produto inválido");
        }
        return produtoRepository.save(produto);
    }

    // Altera a disponibilidade de um produto
    public Product updateAvailable(Long id, boolean available) {
        Product produto = findById(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produto.setAvailable(available);
        return produtoRepository.save(produto);
    }

    // Busca todos os produtos por ID da categoria
    public List<Product> findAllByCategoryId(Long categoryId) {
        if (categoryId == null || categoryId <= 0) {
            throw new IllegalArgumentException("ID da categoria inválido");
        }
        return produtoRepository.findAllByCategoryId(categoryId);
    }
}
