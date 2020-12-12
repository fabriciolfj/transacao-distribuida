package com.github.fabriciolfj.contaservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransacaoMessage {

    private String transactionId;
    private String serviceName;
    private String status;
}
