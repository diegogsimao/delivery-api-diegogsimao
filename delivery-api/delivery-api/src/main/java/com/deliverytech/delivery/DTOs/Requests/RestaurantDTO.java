package com.deliverytech.delivery.DTOs.Requests;

import java.math.BigDecimal;

import com.deliverytech.delivery.validation.Interface.validCep;
import com.deliverytech.delivery.validation.Interface.validTelephone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class RestaurantDTO {

    private Long id;

    @validCep
    private String cep;

    @NotBlank(message = "Descrição é obrigatória")
    private String description;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;

    @NotBlank(message = "Categoria é obrigatória")
    private String category;

    @NotBlank(message = "Endereço é obrigatório")
    private String address;

    @validTelephone
    private String phone;

    @Positive(message = "A taxa de entrega deve ser maior que zero")
    private BigDecimal deliveryFee;

    private boolean active = true;

    private BigDecimal rating;
}
