package com.github.fabriciolfj.contaservice.domain.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.contaservice.api.exceptions.ProcessarTransacaoException;
import com.github.fabriciolfj.contaservice.domain.event.ContaTransacaoEvent;
import com.github.fabriciolfj.contaservice.domain.integracao.TransacaoBinder;
import com.github.fabriciolfj.contaservice.domain.model.TransacaoMessage;
import com.github.fabriciolfj.contaservice.domain.model.TransacaoStatus;
import com.github.fabriciolfj.contaservice.domain.redisrepository.TransacaoDistribuidaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContaTransacaoEventListener {

    private final TransacaoBinder binder;
    private final TransacaoDistribuidaRepository transacaoDistribuidaRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleEvent(final ContaTransacaoEvent event) {
        transacaoDistribuidaRepository.get(event.getTransactionId())
                .orElseThrow(() -> new ProcessarTransacaoException("Transacao nao encontrada"));
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleDepoisRollback(final ContaTransacaoEvent event) {
        send(TransacaoStatus.TO_ROLLBACK.toString(), event.getTransactionId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void handleDepoisFinalizado(final ContaTransacaoEvent event) {
        send(TransacaoStatus.CONFIRMED.toString(), event.getTransactionId());
    }

    private void send(final String status, final String transactionId) {
        final var mapper = new ObjectMapper();
        final var transacaoMsg = TransacaoMessage
                .builder()
                .serviceName("conta-service")
                .transactionId(transactionId)
                .status(status)
                .build();

        try {
            final var json= mapper.writeValueAsString(transacaoMsg);
            final var msg = MessageBuilder.withPayload(json)
                    .build();

            log.info(json);
            binder.output().send(msg);
        } catch (Exception e) {
            throw new ProcessarTransacaoException(e.getMessage());
        }
    }
}
