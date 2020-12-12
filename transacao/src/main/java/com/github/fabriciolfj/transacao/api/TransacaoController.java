package com.github.fabriciolfj.transacao.api;

import com.github.fabriciolfj.transacao.domain.model.TransacaoDistribuida;
import com.github.fabriciolfj.transacao.domain.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TransacaoDistribuida criar() {
        return transacaoService.criarTransacao();
    }
}
