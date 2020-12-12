package com.github.fabriciolfj.contaservice.domain.repository;

import com.github.fabriciolfj.contaservice.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByCustomerId(final String customerId);
}
