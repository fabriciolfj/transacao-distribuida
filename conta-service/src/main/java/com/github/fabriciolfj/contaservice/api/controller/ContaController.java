package com.github.fabriciolfj.contaservice.api.controller;

import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import com.github.fabriciolfj.contaservice.domain.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    @PutMapping("/{customerId}/debitar/{valor}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Conta debitarValor(@PathVariable("customerId") final String customerId, @PathVariable("valor") final BigDecimal valor,
                              @RequestHeader("x-transaction-id") final String transactionId) {
        return contaService.transferir(transactionId, customerId, valor);
    }

}
