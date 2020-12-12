package com.github.fabriciolfj.contaservice.domain.service;

import com.github.fabriciolfj.contaservice.api.exceptions.ProcessarTransacaoException;
import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import com.github.fabriciolfj.contaservice.domain.event.ContaTransacaoEvent;
import com.github.fabriciolfj.contaservice.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
@Async
public class ContaService {

    private final ContaRepository contaRepository;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Conta transferir(final String transactionId, final String customerId, final BigDecimal valor) {
        return contaRepository.findByCustomerId(customerId)
                .map(conta -> {
                    conta.debit(valor);
                    publisher.publishEvent(new ContaTransacaoEvent(transactionId, conta));
                    return contaRepository.save(conta);
                }).orElseThrow(() -> {
                    log.info("Customer nao localizado: {}", customerId);
                    return new ProcessarTransacaoException("Falha ao processar a transação");
                });
    }
}
