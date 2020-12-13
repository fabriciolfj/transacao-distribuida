package com.github.fabriciolfj.pedido.domain.facade;

import com.github.fabriciolfj.pedido.api.dto.PedidoRequest;
import com.github.fabriciolfj.pedido.domain.entity.Pedido;
import com.github.fabriciolfj.pedido.domain.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PedidoCreate {

    public Pedido execute(final PedidoRequest pedidoRequest) {
        return criarPedido(pedidoRequest);
    }

    private Pedido criarPedido(final PedidoRequest request) {
        return Pedido.builder()
                .descricao(request.getDescricao())
                .status(Status.NOVO)
                .valor(request.getValor())
                .cliente(request.getCustomerId())
                .transacao(UUID.randomUUID().toString())
                .build();
    }
}
