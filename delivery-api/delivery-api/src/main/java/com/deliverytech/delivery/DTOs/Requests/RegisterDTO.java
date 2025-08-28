package com.deliverytech.delivery.DTOs.Requests;

import com.deliverytech.delivery.entity.enums.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {

}
