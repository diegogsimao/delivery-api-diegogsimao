package com.deliverytech.delivery.DTOs.Requests;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class RestaurantDTO {
    private Long id;
    private String description;
    private String name;
    private String category;
    private String address;
    private String phone;
    private BigDecimal deliveryFee;
    private boolean active = true;
    private BigDecimal rating;
}
