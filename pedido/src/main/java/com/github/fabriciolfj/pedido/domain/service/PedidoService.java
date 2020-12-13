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

    public void criarTransacao(final PedidoRequest pedidoRequest) {
        final var pedido = pedidoCreate.execute(pedidoRequest);
        salvar(pedido);
        publisherPedido.send(pedido);

    }

    public void send(final Pedido pedido) {
        publisherPedido.send(pedido);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Pedido salvar(final Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
