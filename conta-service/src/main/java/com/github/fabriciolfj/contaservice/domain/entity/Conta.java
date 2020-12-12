 package com.github.fabriciolfj.contaservice.domain.entity;

import com.github.fabriciolfj.contaservice.api.exceptions.SaldoNegativoException;
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
        this.reserva = this.reserva.subtract(valor);

        if (this.reserva.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new SaldoNegativoException("Saldo insuficiente. Valor: " + this.reserva);
        }
    }
}
