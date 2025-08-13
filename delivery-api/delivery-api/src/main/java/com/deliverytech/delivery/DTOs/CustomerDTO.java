package com.deliverytech.delivery.DTOs;

import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class CustomerDTO {
    private Long id = 0L;
    private String name = "";
    private String email = "";
    private String phone = "";
    private String address = "";
    private LocalDateTime registrationDate = LocalDateTime.now();
    private boolean active;
}
