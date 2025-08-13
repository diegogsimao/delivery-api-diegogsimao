package com.deliverytech.delivery.DTOs;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class ProductDTO {
    private Long id;
    private String description;
    private String name;
    private BigDecimal price;
    private String category;
    private boolean available = true;
}
