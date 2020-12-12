package com.github.fabriciolfj.transacao.domain.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.transacao.domain.model.TransacaoMessage;
import com.github.fabriciolfj.transacao.domain.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransacaoListener {

    private final TransacaoService transacaoService;

    @KafkaListener(topics = "${app.topic}")
    public void listen(final String msg) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        transacaoService.salvar(mapper.readValue(msg, TransacaoMessage.class));
    }
}
