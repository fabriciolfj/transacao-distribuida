package com.github.fabriciolfj.pedido.api.controller;

import com.github.fabriciolfj.pedido.api.dto.PedidoRequest;
import com.github.fabriciolfj.pedido.domain.entity.Pedido;
import com.github.fabriciolfj.pedido.domain.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Pedido> findAll() {
        return pedidoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody PedidoRequest request) {
        pedidoService.criarTransacao(request);
    }
}
