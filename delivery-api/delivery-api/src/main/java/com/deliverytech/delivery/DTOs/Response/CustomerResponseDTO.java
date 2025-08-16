package com.deliverytech.delivery.DTOs.Response;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerResponseDTO {
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @Email
    private String email;

    @Size(max = 15)
    private String phone;

    @Size(max = 100)
    private String address;

    private LocalDateTime registrationDate = LocalDateTime.now();
    private boolean active;
}
