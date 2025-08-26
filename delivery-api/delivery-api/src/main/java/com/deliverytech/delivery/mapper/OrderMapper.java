package com.deliverytech.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.deliverytech.delivery.DTOs.Requests.OrderDTO;
import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.entity.Customer;
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.entity.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends GenericMapper<Order, OrderDTO> {

    OrderMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(OrderMapper.class);

    // Entity -> DTO
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "restaurant.id", target = "restaurantId")
    OrderDTO toTarget(Order order);

    // DTO -> Entity
    @Mapping(target = "customer", expression = "java(toCustomer(dto.getCustomerId()))")
    @Mapping(target = "restaurant", expression = "java(toRestaurant(dto.getRestaurantId()))")
    Order toSource(OrderDTO dto);

    // helpers para instanciar apenas pelo id
    default Customer toCustomer(Long id) {
        if (id == null)
            return null;
        Customer c = new Customer();
        c.setId(id);
        return c;
    }

    default Restaurant toRestaurant(Long id) {
        if (id == null)
            return null;
        Restaurant r = new Restaurant();
        r.setId(id);
        return r;
    }

    OrderResponseDTO toOrderResponseDTO(Order target);

    List<OrderResponseDTO> toOrderResponseDTOList(List<Order> target);
}
