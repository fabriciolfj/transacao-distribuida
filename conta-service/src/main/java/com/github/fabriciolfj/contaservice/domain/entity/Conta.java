package com.github.fabriciolfj.contaservice.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "customer_id")
    private String customerId;
    private BigDecimal reserva;

    public void debit(final BigDecimal valor) {
        this.reserva.subtract(valor);
    }
}
