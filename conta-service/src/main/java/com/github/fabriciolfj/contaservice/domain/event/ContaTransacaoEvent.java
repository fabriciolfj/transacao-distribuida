package com.github.fabriciolfj.contaservice.domain.event;

import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaTransacaoEvent {

    private String transactionId;
    private Conta conta;
}
