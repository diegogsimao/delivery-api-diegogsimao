package com.deliverytech.delivery.DTOs.Requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private BigDecimal valorTotal;
    private String status;
    private LocalDateTime dataPedido;

    @NotBlank(message = "Forma de pagamento é obrigatória")
    @Pattern(regexp = "^(DINHEIRO|CARTAO_CREDITO|CARTAO_DEBITO|PIX)$", message = "Forma de pagamento deve ser: DINHEIRO, CARTAO_CREDITO, CARTAO_DEBITO ou PIX")
    private String formaPagamento;

    // relacionamentos como apenas IDs
    private Long customerId;
    private Long restaurantId;
}
