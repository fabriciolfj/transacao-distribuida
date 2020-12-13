package com.github.fabriciolfj.pedido.domain.integracao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.pedido.domain.entity.Pedido;
import com.github.fabriciolfj.pedido.domain.model.TransacaoMessage;
import com.github.fabriciolfj.pedido.domain.model.TransacaoStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;


@Component
@Slf4j
public class PublisherPedido {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${app.topic.create}")
    private String topic;

    public void send(final Pedido pedido) {
        try {
            final var json = objectMapper.writeValueAsString(criarMsg(pedido));
            final var msg = MessageBuilder.withPayload(json)
                    .setHeader(KafkaHeaders.TOPIC, topic)
                    .build();

            kafkaTemplate.send(msg);
            kafkaTemplate.flush();
            kafkaTemplate.setCloseTimeout(Duration.ofMillis(1000));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Falha no processo de envio do pedido para a transacao");
        }

    }

    private TransacaoMessage criarMsg(final Pedido pedido) {
        return TransacaoMessage.builder()
                .serviceName("pedido")
                .status(TransacaoStatus.NEW.toString())
                .transactionId(pedido.getTransacao())
                .build();
    }
}
