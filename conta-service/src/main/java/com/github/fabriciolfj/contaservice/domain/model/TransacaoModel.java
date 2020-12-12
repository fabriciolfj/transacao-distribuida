package com.github.fabriciolfj.contaservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoModel {

    private String transacao;
    private String customerId;
    private BigDecimal valor;
}
