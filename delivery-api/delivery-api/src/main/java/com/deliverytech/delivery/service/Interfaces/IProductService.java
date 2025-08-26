package com.deliverytech.delivery.service.Interfaces;

import java.util.List;

import com.deliverytech.delivery.DTOs.Requests.ProductDTO;
import com.deliverytech.delivery.DTOs.Response.ProductResponseDTO;

public interface IProductService {

    public ProductResponseDTO create(ProductDTO produto);

    public ProductResponseDTO findByRestauranteId(Long restauranteId);

    public List<ProductResponseDTO> findByAvailableTrueAndRestaurantsId(long restaurantId);

    public ProductResponseDTO findById(Long id);

    public ProductResponseDTO update(ProductDTO produto);

    // Altera a disponibilidade de um produto'
    public ProductResponseDTO updateAvailable(Long id, boolean available);

    public void delete(Long id);

}
