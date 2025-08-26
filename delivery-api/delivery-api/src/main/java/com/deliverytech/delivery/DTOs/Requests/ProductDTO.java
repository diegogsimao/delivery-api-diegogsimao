package com.deliverytech.delivery.DTOs.Requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String description;
    private String name;
    private BigDecimal price;
    private String category;
    private boolean available = true;
}
