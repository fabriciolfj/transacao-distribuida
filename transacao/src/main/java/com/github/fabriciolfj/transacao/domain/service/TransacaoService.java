package com.github.fabriciolfj.transacao.domain.service;

import com.github.fabriciolfj.transacao.domain.cache.TransacaoDistribuidaCache;
import com.github.fabriciolfj.transacao.domain.entity.Transacao;
import com.github.fabriciolfj.transacao.domain.model.TransacaoDistribuida;
import com.github.fabriciolfj.transacao.domain.model.TransacaoMessage;
import com.github.fabriciolfj.transacao.domain.model.TransacaoStatus;
import com.github.fabriciolfj.transacao.domain.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final TransacaoDistribuidaCache cache;

    public void salvar(final TransacaoMessage msg) {
        var entity = Transacao
                .builder()
                .status(msg.getStatus())
                .transactionId(msg.getTransactionId())
                .build();

        transacaoRepository.save(entity);
    }

    public TransacaoDistribuida criarTransacao() {
        var transacao = TransacaoDistribuida
                .builder()
                .status(TransacaoStatus.NEW.toString())
                .transactionId(UUID.randomUUID().toString())
                .build();

        log.info(transacao.toString());
        cache.addTransacao(transacao);

        return transacao;
    }
}
