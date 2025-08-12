package com.deliverytech.delivery.DTOs;

import lombok.Data;

@Data
public class RestaurantDTO {

    private String name;
    private String description;

    public RestaurantDTO() {
        super();
    }

    public RestaurantDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "RestauranteDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
