package com.deliverytech.delivery.service.Interfaces;

import com.deliverytech.delivery.DTOs.Requests.OrderDTO;
import com.deliverytech.delivery.DTOs.Response.OrderResponseDTO;

public interface IOrderService {

    public OrderResponseDTO createOrder(OrderDTO pedido);

    public OrderResponseDTO update(OrderDTO pedido);

    public void delete(Long id);

    public OrderResponseDTO findById(Long id);

    // public OrderResponseDTO updateOrderStatus(Long id, OrderStatus status);

    public OrderResponseDTO cancelOrder(Long id);

}
