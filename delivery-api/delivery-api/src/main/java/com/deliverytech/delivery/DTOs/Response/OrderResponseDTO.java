package com.deliverytech.delivery.DTOs.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class OrderResponseDTO {
    private Long id;
    private BigDecimal valorTotal;
    private String status;
    private LocalDateTime dataPedido;
}
