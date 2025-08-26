package com.deliverytech.delivery.service.Interfaces;

import java.util.List;
import com.deliverytech.delivery.DTOs.Requests.RestaurantDTO;
import com.deliverytech.delivery.DTOs.Response.RestaurantResponseDTO;

public interface IRestaurantService {

    public RestaurantResponseDTO create(RestaurantDTO restaurant);

    public RestaurantResponseDTO findById(Long id);

    public List<RestaurantResponseDTO> getAllByCategory(String category);

    public RestaurantResponseDTO update(RestaurantDTO restaurant);

    public RestaurantResponseDTO updateActive(RestaurantDTO restaurant);

    public void delete(Long id);

    public double calculateDeliveryFee(Long id, String cep);

    public List<RestaurantResponseDTO> findByActive();

    public List<RestaurantResponseDTO> findByCep(String cep);

    public Double calcularTaxaEntrega(Long id, String cep);
}
