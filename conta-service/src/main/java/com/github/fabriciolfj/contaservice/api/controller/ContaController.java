package com.github.fabriciolfj.contaservice.api.controller;

import com.github.fabriciolfj.contaservice.api.dto.request.TransacaoRequest;
import com.github.fabriciolfj.contaservice.api.mappers.TransacaoModelMapper;
import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import com.github.fabriciolfj.contaservice.domain.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private TransacaoModelMapper mapper;

    @PutMapping("/debitar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Conta debitarValor(@Valid @RequestBody TransacaoRequest request,
                              @RequestHeader("x-transaction-id") final String transactionId) {
        return contaService.transferir(mapper.toModel(request, transactionId));
    }

}
