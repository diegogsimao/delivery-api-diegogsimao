package com.deliverytech.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deliverytech.delivery.DTOs.Requests.OrderDTO;
import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.entity.Order;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends GenericMapper<Order, OrderDTO> {

    OrderResponseDTO toOrderResponseDTO(Order target);

    List<OrderResponseDTO> toOrderResponseDTOList(List<Order> target);
}
