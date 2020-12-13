package com.github.fabriciolfj.pedido.domain.repository;

import com.github.fabriciolfj.pedido.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
