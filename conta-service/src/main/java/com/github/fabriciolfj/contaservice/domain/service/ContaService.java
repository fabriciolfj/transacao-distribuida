package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.exceptions.ProcessarTransacaoException;
import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import com.github.fabriciolfj.contaservice.domain.event.ContaTransacaoEvent;
import com.github.fabriciolfj.contaservice.domain.model.TransacaoModel;
import com.github.fabriciolfj.contaservice.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
@Async
public class ContaService {

    private final ContaRepository contaRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Conta transferir(final TransacaoModel model) {
        return contaRepository.findByCustomerId(model.getCustomerId())
                .map(conta -> {
                    conta.debit(model.getValor());
                    publisher.publishEvent(new ContaTransacaoEvent(model.getTransacao(), conta));
                    return contaRepository.save(conta);
                }).orElseThrow(() -> {
                    log.info("Customer nao localizado: {}", model.getCustomerId());
                    return new ProcessarTransacaoException("Falha ao processar a transação");
                });
    }
}
