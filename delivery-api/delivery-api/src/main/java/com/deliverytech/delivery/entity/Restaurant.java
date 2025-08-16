package com.deliverytech.delivery.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String name;

    private String cep;
    private String category;
    private String address;
    private String phone;
    private BigDecimal deliveryFee;
    private boolean active = true;
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
