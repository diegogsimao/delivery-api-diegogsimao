package com.deliverytech.delivery.service.Interfaces;

import java.util.List;

import com.deliverytech.delivery.DTOs.Requests.OrderDTO;
import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;
import com.deliverytech.delivery.entity.Order;
import com.deliverytech.delivery.entity.OrderItem;
import com.deliverytech.delivery.entity.enums.OrderStatus;

public interface IOrderService {
    public OrderResponseDTO create(OrderDTO pedido);

    public Order update(Order pedido);

    public void delete(Long id);

    public Order findById(Long id);

    public List<Order> findByCustomerId(Long customerId);

    public Order updateOrderStatus(Long id, OrderStatus status);

    public Order calculateTotalOrder(List<OrderItem> itens);

    public Order cancelOrder(Long id);

    public List<Order> findByRestaurantId(Long restauranteId);
}
