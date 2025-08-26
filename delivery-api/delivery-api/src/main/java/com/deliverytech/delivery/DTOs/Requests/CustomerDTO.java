package com.deliverytech.delivery.DTOs.Requests;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @NotNull
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @NotNull
    @Email
    private String email;

    @Size(max = 15)
    private String phone;

    @Size(max = 100)
    private String address;

    private LocalDateTime registrationDate = LocalDateTime.now();
    private boolean active;
}
