package com.deliverytech.delivery.DTOs;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class OrderDTO {
    private Long id;
    private BigDecimal valorTotal;
    private String status;
    private LocalDateTime dataPedido;
}
