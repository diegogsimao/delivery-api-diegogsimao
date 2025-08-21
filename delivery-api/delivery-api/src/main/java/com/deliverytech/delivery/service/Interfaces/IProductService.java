package com.deliverytech.delivery.service.Interfaces;

import java.util.List;

import com.deliverytech.delivery.entity.Product;

public interface IProductService {

    public Product create(Product produto);

    public List<Product> findByRestauranteId(Long restauranteId);

    public List<Product> findByAvailableTrueAndRestaurantsId(long restaurantId);

    public Product findById(Long id);

    public Product update(Product produto);

    // Altera a disponibilidade de um produto'
    public Product updateAvailable(Long id, boolean available);

    // Busca todos os produtos por ID da categoria
    public List<Product> findAllByCategoryName(String categoryName);

    public void delete(Long id);

    public List<Product> findByName(String nome);

}
