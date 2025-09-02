package com.deliverytech.delivery.DTOs.Requests;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10,message = "Descrição deve ter no mínimo 10 caracteres")
    private String description;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2,max = 50,message = "Nome deve ter entre 2 e 50 caracteres")
    private String name;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", inclusive = true, message = "O preço deve ser maior que zero")
    @DecimalMax(value = "500.00", inclusive = true, message = "O preço deve ser no máximo R$ 500,00")
    private BigDecimal price;

    @NotNull(message = "A categoria é obrigatória")
    private String category;

    private boolean available = true;
}
