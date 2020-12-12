package com.github.fabriciolfj.transacao.domain.repository;

import com.github.fabriciolfj.transacao.domain.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
