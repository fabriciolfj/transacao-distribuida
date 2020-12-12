package com.github.fabriciolfj.contaservice.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoRequest {

    @NotBlank(message = "Informe o id do cliente")
    private String customerId;
    @Positive(message = "Informe uma valor positivo")
    private BigDecimal valor;
}
