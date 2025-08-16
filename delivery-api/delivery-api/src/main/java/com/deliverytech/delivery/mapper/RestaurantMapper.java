package com.deliverytech.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.deliverytech.delivery.DTOs.Requests.RestaurantDTO;
import com.deliverytech.delivery.DTOs.Response.RestaurantResponseDTO;
import com.deliverytech.delivery.entity.Restaurant;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper extends GenericMapper<Restaurant, RestaurantDTO> {
    RestaurantResponseDTO toProductResponseDTO(Restaurant target);

    List<RestaurantResponseDTO> toProductResponseDTOList(List<Restaurant> target);

}
