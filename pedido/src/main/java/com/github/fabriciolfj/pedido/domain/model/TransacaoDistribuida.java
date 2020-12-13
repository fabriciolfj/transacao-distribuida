package com.github.fabriciolfj.pedido.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoDistribuida {

    private String transactionId;
    private String status;
}
