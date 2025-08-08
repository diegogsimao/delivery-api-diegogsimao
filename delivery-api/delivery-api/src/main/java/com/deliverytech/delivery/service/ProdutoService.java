package com.deliverytech.delivery.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.Produto;
import com.deliverytech.delivery.entity.Restaurante;
import com.deliverytech.delivery.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto Create(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto Update(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void Delete(Long id) {
        produtoRepository.deleteById(id);
    }

    public boolean ValidarDadosProduto(Produto produto) {

        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        // Lógica para validar os dados do produto
        return true;
    }

    public List<Restaurante> BuscarProdutosPorRestaurante(Long restauranteId) {
        return produtoRepository.ObterProdutosPorRestaurante(restauranteId);
    }

    public boolean ValidarPrecoProduto(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        return produto.getPreco() != null && produto.getPreco().compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean ObterDisponibilidadeProduto(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        return produto.isDisponivel();
    }
}
