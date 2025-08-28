package com.deliverytech.delivery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.delivery.DTOs.Requests.ProductDTO;
import com.deliverytech.delivery.DTOs.Response.ProductResponseDTO;
import com.deliverytech.delivery.entity.Product;
import com.deliverytech.delivery.mapper.ProductMapper;
import com.deliverytech.delivery.repository.ProductRepository;
import com.deliverytech.delivery.service.Interfaces.IProductService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService implements IProductService {

    private ProductRepository produtoRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductService(
            ProductRepository produtoRepository,
            ProductMapper productMapper) {

        this.produtoRepository = produtoRepository;
        this.productMapper = productMapper;
    }

    // Cria um produto
    @Transactional
    public ProductResponseDTO create(ProductDTO produto) {

        if (produto == null) {
            throw new EntityNotFoundException("Produto não pode ser nulo");
        }

        Product produtoSalvo = produtoRepository.save(productMapper.toSource(produto));
        return productMapper.toProductResponseDTO(produtoSalvo);
    }

    // Busca todos os produtos por ID do restaurante
    public ProductResponseDTO findByRestauranteId(Long restauranteId) {

        if (restauranteId == null || restauranteId <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido");
        }
        return productMapper.toProductResponseDTO(
                produtoRepository.findByRestaurantsId(restauranteId));
    }

    // Busca todos os produtos ativos por ID do restaurante
    public List<ProductResponseDTO> findByAvailableTrueAndRestaurantsId(long restaurantId) {
        if (restaurantId <= 0) {
            throw new IllegalArgumentException("ID do restaurante inválido");
        }
        return productMapper.toProductResponseDTOList(
                produtoRepository.findByAvailableTrueAndRestaurantsId(restaurantId));
    }

    // Busca um produto por ID
    public ProductResponseDTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        return produtoRepository.findById(id)
                .map(productMapper::toProductResponseDTO)
                .orElse(null);
    }

    // Atualiza um produto
    @Transactional
    public ProductResponseDTO update(ProductDTO produto) {

        if (produto == null || produto.getId() == null || produto.getId() <= 0) {
            throw new IllegalArgumentException("Produto inválido");
        }
        Product produtoAtualizado = produtoRepository.save(productMapper.toSource(produto));
        return productMapper.toProductResponseDTO(produtoAtualizado);
    }

    // Altera a disponibilidade de um produto
    public ProductResponseDTO updateAvailable(Long id, boolean available) {

        Product produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.setAvailable(available);
        return productMapper.toProductResponseDTO(produtoRepository.save(produto));
    }

    public void delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        produtoRepository.deleteById(id);
    }
}
