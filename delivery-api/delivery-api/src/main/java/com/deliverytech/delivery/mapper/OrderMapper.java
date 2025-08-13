package com.deliverytech.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.deliverytech.delivery.DTOs.OrderDTO;
import com.deliverytech.delivery.entity.Order;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends GenericMapper<Order, OrderDTO> {

}
