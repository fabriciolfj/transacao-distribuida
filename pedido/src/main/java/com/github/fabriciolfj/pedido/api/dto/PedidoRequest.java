package com.github.fabriciolfj.pedido.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoRequest {

    private String descricao;
    private String customerId;
    private BigDecimal valor;
}
