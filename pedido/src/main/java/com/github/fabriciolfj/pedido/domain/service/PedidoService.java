package com.github.fabriciolfj.pedido.domain.service;

import com.github.fabriciolfj.pedido.api.dto.PedidoRequest;
import com.github.fabriciolfj.pedido.domain.entity.Pedido;
import com.github.fabriciolfj.pedido.domain.facade.PedidoCreate;
import com.github.fabriciolfj.pedido.domain.integracao.PublisherPedido;
import com.github.fabriciolfj.pedido.domain.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PedidoService {

    private final PedidoCreate pedidoCreate;
    private final PedidoRepository pedidoRepository;
    private final PublisherPedido publisherPedido;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Transactional("chainedKafkaTransactionManager")
    public void criarTransacao(final PedidoRequest pedidoRequest) {
        final var pedido = pedidoCreate.execute(pedidoRequest);
        pedidoRepository.save(pedido);
        publisherPedido.send(pedido);

    }
}
